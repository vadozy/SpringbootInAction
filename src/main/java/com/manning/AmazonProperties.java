package com.manning;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by vstorozhuk on 8/12/2016.
 */
@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {

	private String associateId;

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getAssociateId() {
		return associateId;
	}

}
