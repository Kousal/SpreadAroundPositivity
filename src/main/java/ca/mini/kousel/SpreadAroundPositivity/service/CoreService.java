package ca.mini.kousel.SpreadAroundPositivity.service;

import ca.mini.kousel.SpreadAroundPositivity.entity.spreadaroundpositivity;

public interface CoreService {
	public spreadaroundpositivity getUserRecords(String username);
	public boolean createUser(String username);
	public boolean addComments(spreadaroundpositivity usercomments);
}
