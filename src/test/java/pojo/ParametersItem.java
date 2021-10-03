package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class ParametersItem{
	private Object unit;
	private String name;
	private Options options;
	private Restrictions restrictions;
	private boolean requiredForProduct;
	private String id;
	private String type;
	private boolean required;
}