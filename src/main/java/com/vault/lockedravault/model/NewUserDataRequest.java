package com.vault.lockedravault.model;

public record NewUserDataRequest(String domain, String userNameForDomain, String passwordForDomain, String domainName, String domainUrl, String categoryName) {
}
