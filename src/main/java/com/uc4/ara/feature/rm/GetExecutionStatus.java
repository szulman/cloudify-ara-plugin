package com.uc4.ara.feature.rm;

import javax.xml.xpath.XPathExpressionException;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;

public class GetExecutionStatus extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> executionIdArg;
	private CmdLineParser.Option<String> intervalArg;
	private CmdLineParser.Option<String> maximumWaitTimeArg;

    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Gets a Execution's Status from a executionId");
        parser.setExamples(
                "java -jar dm-tool.jar rm GetExecutionStatus --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -e \"test executionId\" -i \"test interval(s)\" -m \"test maximum wait-time(m)\"" +
                        "\r\njava -jar dm-tool.jar rm GetExecutionStatus --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --execution \"test executionId\" --interval \"60\" --wait-time \"60\""
                );

        executionIdArg = parser.addHelp(
                parser.addStringOption("e", "execution", true),
                "The id of the created execution within the Deployment manager.");

        intervalArg = parser.addHelp(
                parser.addStringOption("i", "interval", false),
                "Interval in seconds.");

        maximumWaitTimeArg = parser.addHelp(
                parser.addStringOption("m", "wait-time", false),
                "Will wait for completion until maximum wait-time reached. The unit is minutes.");

    }


    @Override
    public int run(String[] args) throws Exception {
        super.run(args);

        String executionId = parser.getOptionValue(executionIdArg);

        String intervalStr = parser.getOptionValue(intervalArg);
        int interval = isNumber(intervalStr)?Integer.parseInt(intervalStr):60;
        
        String maximumWaitTimeStr = parser.getOptionValue(maximumWaitTimeArg);
        int maximumWaitTime = isNumber(maximumWaitTimeStr)?Integer.parseInt(maximumWaitTimeStr):60;

        long currentTime = System.currentTimeMillis();
        String statusValue = "";
        while((System.currentTimeMillis() - currentTime)/(1000*60) < maximumWaitTime) {
        	try {
                statusValue = searchOneProperty(service, "Execution", "system_id", executionId, "system_status");
                FeatureUtil.logMsg("RESULT-PAIR: system_status = " + statusValue);

            } catch (XPathExpressionException e) {
                FeatureUtil.logMsg("The execution's status does not exist. Message: " + e.getMessage(), MsgTypes.EXCEPTION);
                    return ErrorCodes.EXCEPTION;

            } catch (Exception e) {
                FeatureUtil.logMsg("Failed to get the execution's status. Message: " + e.getMessage(), MsgTypes.EXCEPTION);
                return ErrorCodes.EXCEPTION;
            }

        	if (statusValue.equalsIgnoreCase("Finished")) {
        		return ErrorCodes.OK;
        	} else if(statusValue.equalsIgnoreCase("Failed") || statusValue.equalsIgnoreCase("Canceled") 
        			|| statusValue.equalsIgnoreCase("Revoked") || statusValue.equalsIgnoreCase("Rejected")) {
        		FeatureUtil.logMsg("The execution's status is Failed or Canceled or Revoked or Rejected.", MsgTypes.ERROR);
        		return ErrorCodes.ERROR;
        	} else if (statusValue.equalsIgnoreCase("Error")) {
        		FeatureUtil.logMsg("Error to get the execution's status.", MsgTypes.FAULT_OTHER);
        		return ErrorCodes.FAULT_OTHER;
        	}
        	Thread.sleep(interval*1000, 0);
        }

        FeatureUtil.logMsg("Time out to get the execution's status.", MsgTypes.FAULT_OTHER);
        return ErrorCodes.FAULT_OTHER;
    }

    private boolean isNumber(String s) {
    	try {
    		Integer.parseInt(s);
    	} catch (Exception e) {
    		return false;
    	}
    	return true;
    }
}
