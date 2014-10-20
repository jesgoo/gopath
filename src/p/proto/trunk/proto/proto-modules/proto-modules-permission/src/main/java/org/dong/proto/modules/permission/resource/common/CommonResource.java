package org.dong.proto.modules.permission.resource.common;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.resource.Resource;

@Entity
@DiscriminatorValue(value=OSPConstants.RESOURCE_TYPE_COMMON)
public class CommonResource extends Resource {
}
