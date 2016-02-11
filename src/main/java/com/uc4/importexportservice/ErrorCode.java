
package com.uc4.importexportservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="InvalidOptions"/>
 *     &lt;enumeration value="InvalidInputFile"/>
 *     &lt;enumeration value="AuthenticationError"/>
 *     &lt;enumeration value="AuthorizationError"/>
 *     &lt;enumeration value="SchemaValidationError"/>
 *     &lt;enumeration value="BusinessRuleViolation"/>
 *     &lt;enumeration value="MaintypeIsRequired"/>
 *     &lt;enumeration value="NoMaintypeExpected"/>
 *     &lt;enumeration value="InvalidDataFormat"/>
 *     &lt;enumeration value="MaintypeNotFound"/>
 *     &lt;enumeration value="CustomTypeNotFound"/>
 *     &lt;enumeration value="TypeVersionNotFound"/>
 *     &lt;enumeration value="WronglyFormattedDate"/>
 *     &lt;enumeration value="InvalidFormatOption"/>
 *     &lt;enumeration value="InvalidCountOption"/>
 *     &lt;enumeration value="PropertyRuleViolated"/>
 *     &lt;enumeration value="DatatypeMismatch"/>
 *     &lt;enumeration value="ReferenceNotFound"/>
 *     &lt;enumeration value="PropertyDoesnotExists"/>
 *     &lt;enumeration value="ObjectIdentityAlreadyExists"/>
 *     &lt;enumeration value="MandatoryPropertiesMissing"/>
 *     &lt;enumeration value="InvalidIndentityProperty"/>
 *     &lt;enumeration value="SystemPropertyIsReadOnly"/>
 *     &lt;enumeration value="DuplicateProperty"/>
 *     &lt;enumeration value="WrongFormatPropertyName"/>
 *     &lt;enumeration value="NoPropertyForDeletion"/>
 *     &lt;enumeration value="PropertiesCannotBeUsedTogetherForDeletion"/>
 *     &lt;enumeration value="OperatorDoesNotSupport"/>
 *     &lt;enumeration value="ConditionIsInvalid"/>
 *     &lt;enumeration value="ConditionOperatorIsInvalid"/>
 *     &lt;enumeration value="ConditionPropertyDoesnotSupport"/>
 *     &lt;enumeration value="FolderLogsIsNotWriteable"/>
 *     &lt;enumeration value="NoCreationRightOnCustomType"/>
 *     &lt;enumeration value="NoWriteAccessOnObject"/>
 *     &lt;enumeration value="NoReadAccessOnObject"/>
 *     &lt;enumeration value="UnexpectedError"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorCode")
@XmlEnum
public enum ErrorCode {

    @XmlEnumValue("InvalidOptions")
    INVALID_OPTIONS("InvalidOptions"),
    @XmlEnumValue("InvalidInputFile")
    INVALID_INPUT_FILE("InvalidInputFile"),
    @XmlEnumValue("AuthenticationError")
    AUTHENTICATION_ERROR("AuthenticationError"),
    @XmlEnumValue("AuthorizationError")
    AUTHORIZATION_ERROR("AuthorizationError"),
    @XmlEnumValue("SchemaValidationError")
    SCHEMA_VALIDATION_ERROR("SchemaValidationError"),
    @XmlEnumValue("BusinessRuleViolation")
    BUSINESS_RULE_VIOLATION("BusinessRuleViolation"),
    @XmlEnumValue("MaintypeIsRequired")
    MAINTYPE_IS_REQUIRED("MaintypeIsRequired"),
    @XmlEnumValue("NoMaintypeExpected")
    NO_MAINTYPE_EXPECTED("NoMaintypeExpected"),
    @XmlEnumValue("InvalidDataFormat")
    INVALID_DATA_FORMAT("InvalidDataFormat"),
    @XmlEnumValue("MaintypeNotFound")
    MAINTYPE_NOT_FOUND("MaintypeNotFound"),
    @XmlEnumValue("CustomTypeNotFound")
    CUSTOM_TYPE_NOT_FOUND("CustomTypeNotFound"),
    @XmlEnumValue("TypeVersionNotFound")
    TYPE_VERSION_NOT_FOUND("TypeVersionNotFound"),
    @XmlEnumValue("WronglyFormattedDate")
    WRONGLY_FORMATTED_DATE("WronglyFormattedDate"),
    @XmlEnumValue("InvalidFormatOption")
    INVALID_FORMAT_OPTION("InvalidFormatOption"),
    @XmlEnumValue("InvalidCountOption")
    INVALID_COUNT_OPTION("InvalidCountOption"),
    @XmlEnumValue("PropertyRuleViolated")
    PROPERTY_RULE_VIOLATED("PropertyRuleViolated"),
    @XmlEnumValue("DatatypeMismatch")
    DATATYPE_MISMATCH("DatatypeMismatch"),
    @XmlEnumValue("ReferenceNotFound")
    REFERENCE_NOT_FOUND("ReferenceNotFound"),
    @XmlEnumValue("PropertyDoesnotExists")
    PROPERTY_DOESNOT_EXISTS("PropertyDoesnotExists"),
    @XmlEnumValue("ObjectIdentityAlreadyExists")
    OBJECT_IDENTITY_ALREADY_EXISTS("ObjectIdentityAlreadyExists"),
    @XmlEnumValue("MandatoryPropertiesMissing")
    MANDATORY_PROPERTIES_MISSING("MandatoryPropertiesMissing"),
    @XmlEnumValue("InvalidIndentityProperty")
    INVALID_INDENTITY_PROPERTY("InvalidIndentityProperty"),
    @XmlEnumValue("SystemPropertyIsReadOnly")
    SYSTEM_PROPERTY_IS_READ_ONLY("SystemPropertyIsReadOnly"),
    @XmlEnumValue("DuplicateProperty")
    DUPLICATE_PROPERTY("DuplicateProperty"),
    @XmlEnumValue("WrongFormatPropertyName")
    WRONG_FORMAT_PROPERTY_NAME("WrongFormatPropertyName"),
    @XmlEnumValue("NoPropertyForDeletion")
    NO_PROPERTY_FOR_DELETION("NoPropertyForDeletion"),
    @XmlEnumValue("PropertiesCannotBeUsedTogetherForDeletion")
    PROPERTIES_CANNOT_BE_USED_TOGETHER_FOR_DELETION("PropertiesCannotBeUsedTogetherForDeletion"),
    @XmlEnumValue("OperatorDoesNotSupport")
    OPERATOR_DOES_NOT_SUPPORT("OperatorDoesNotSupport"),
    @XmlEnumValue("ConditionIsInvalid")
    CONDITION_IS_INVALID("ConditionIsInvalid"),
    @XmlEnumValue("ConditionOperatorIsInvalid")
    CONDITION_OPERATOR_IS_INVALID("ConditionOperatorIsInvalid"),
    @XmlEnumValue("ConditionPropertyDoesnotSupport")
    CONDITION_PROPERTY_DOESNOT_SUPPORT("ConditionPropertyDoesnotSupport"),
    @XmlEnumValue("FolderLogsIsNotWriteable")
    FOLDER_LOGS_IS_NOT_WRITEABLE("FolderLogsIsNotWriteable"),
    @XmlEnumValue("NoCreationRightOnCustomType")
    NO_CREATION_RIGHT_ON_CUSTOM_TYPE("NoCreationRightOnCustomType"),
    @XmlEnumValue("NoWriteAccessOnObject")
    NO_WRITE_ACCESS_ON_OBJECT("NoWriteAccessOnObject"),
    @XmlEnumValue("NoReadAccessOnObject")
    NO_READ_ACCESS_ON_OBJECT("NoReadAccessOnObject"),
    @XmlEnumValue("UnexpectedError")
    UNEXPECTED_ERROR("UnexpectedError");
    private final String value;

    ErrorCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorCode fromValue(String v) {
        for (ErrorCode c: ErrorCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
