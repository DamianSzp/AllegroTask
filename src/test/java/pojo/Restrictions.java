package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class Restrictions{
	private int min;
	private int max;
	private boolean range;
}