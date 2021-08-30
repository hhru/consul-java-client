package ru.hh.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;


@Value.Immutable
@Value.Style(jdkOnly = true)
@JsonSerialize(as = ImmutableRoleResponse.class)
@JsonDeserialize(as = ImmutableRoleResponse.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RoleResponse extends BaseRoleResponse {
}
