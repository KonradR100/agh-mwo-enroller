package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

	Session session;

	public MeetingService() {session = DatabaseConnector.getInstance().getSession();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = this.session.createQuery(hql);
		return query.list();
	}

	public Meeting findById(Long id) {
		return this.session.getSession().get(Meeting.class, id);
	}

	public Meeting add(Meeting meeting) {
		Transaction transaction = this.session.getSession().beginTransaction();
		this.session.getSession().save(meeting);
		transaction.commit();
		return meeting;
	}

	public void update(Meeting meeting) {
		Transaction transaction = this.session.getSession().beginTransaction();
		this.session.getSession().merge(meeting);
		transaction.commit();
	}

	public void remove(Meeting meeting) {
		Transaction transaction = this.session.getSession().beginTransaction();
		this.session.getSession().remove(meeting);
		transaction.commit();
	}
}
