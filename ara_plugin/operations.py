from cloudify import context
from cloudify.decorators import operation as _operation

#from ara_plugin.ara_client import run_ara

EXPECTED_OP_PREFIXES = (
    'cloudify.interfaces.lifecycle',
    'cloudify.interfaces.relationship_lifecycle')


def _extract_op(ctx):
    prefix, _, op = ctx.operation.name.rpartition('.')
    if prefix not in EXPECTED_OP_PREFIXES:
        ctx.logger.warn("Node operation is expected to start with '{0}' "
                        "but starts with '{1}'".format(
                            ' or '.join(EXPECTED_OP_PREFIXES), prefix))
    return op


@_operation
def operation(ctx, **kwargs):
    if ctx.type == context.NODE_INSTANCE:
        properties = ctx.node.properties
    else:
        properties = ctx.source.node.properties
