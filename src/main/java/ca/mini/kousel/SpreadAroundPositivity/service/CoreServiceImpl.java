package ca.mini.kousel.SpreadAroundPositivity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mini.kousel.SpreadAroundPositivity.dao.CoreDAO;
import ca.mini.kousel.SpreadAroundPositivity.entity.spreadaroundpositivity;

@Service
public class CoreServiceImpl implements CoreService{

	@Autowired
	CoreDAO coredao;
	
	public spreadaroundpositivity getUserRecords(String username) {
		// TODO Auto-generated method stub
		return coredao.findByUsername(username);
	}
	public boolean createUser(String username) {
		spreadaroundpositivity newuser = new spreadaroundpositivity();
		if(coredao.findByUsername(username) == null)
		{
			newuser.setUsername(username);
			coredao.insert(newuser);
			return true;
		}
		else
			return false;
	}
	@Override
	public boolean addComments(spreadaroundpositivity usercomments) {
		String username = usercomments.getUsername();
		spreadaroundpositivity user = coredao.findByUsername(username);
		if(user != null)
		{
			if(usercomments.getMessageid() % 1 == 0)
			{
				spreadaroundpositivity newComment = new spreadaroundpositivity();
				newComment.setMessageid(user.getMessages().size()+1);
				newComment.setMessage(usercomments.getMessage());
				user.getMessages().add(newComment);
				coredao.save(user);
				return true;
			}
			else
			{
				float appendingValue = usercomments.getMessageid() % 1;
				float commentBase = (float)Math.floor(usercomments.getMessageid());
				System.out.println("decimal - "+appendingValue);
				System.out.println("base - "+commentBase);
				try {
				for(spreadaroundpositivity everycomment: user.getMessages())
				{
					if(everycomment.getMessageid() == commentBase)
					{
						spreadaroundpositivity newComment = new spreadaroundpositivity();
						float valuetoAdd = Float.parseFloat("0."+(everycomment.getMessages().size()+1));
						newComment.setMessageid(everycomment.getMessageid()+valuetoAdd);
						newComment.setMessage(usercomments.getMessage());
						everycomment.getMessages().add(newComment);
						coredao.save(user);
						return true;
					}
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return false;
			}
		}
		else
			return false;
	}
}
