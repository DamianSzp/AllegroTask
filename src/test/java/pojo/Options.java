package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class Options{
	private Object ambiguousValueId;
	private boolean variantsAllowed;
	private List<String> requiredDependsOnValueIds;
	private String dependsOnParameterId;
	private boolean customValuesEnabled;
	private boolean variantsEqual;
	private List<String> displayDependsOnValueIds;
	private boolean describesProduct;
}