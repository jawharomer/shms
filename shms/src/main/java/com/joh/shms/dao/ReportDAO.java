package com.joh.shms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.joh.shms.domain.model.NotificationD;
import com.joh.shms.domain.model.NotificationD.NotificationType;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<NotificationD> findAdminNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em
				.createNativeQuery("SELECT IFNULL(SUM(IFNULL(QUANTITY,0)-IFNULL(SOLD_AMOUNT,0)),0) AS EXPIRATE\n"
						+ "FROM ORDER_DETAILS WHERE EXPIRATION_DATE<=CURDATE()+INTERVAL 90 DAY AND QUANTITY-SOLD_AMOUNT>0;");

		Object totalExpirationResult = query.getSingleResult();

		int totalExpiration = 0;
		if (totalExpirationResult != null)
			totalExpiration = Integer.parseInt("" + totalExpirationResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Product Expiration");
		not1.setEtc("" + totalExpiration);
		not1.setMessage("Number of Product is About to be expired in stock in next 90 days ");

		not1.setNotificationType(NotificationType.DANGER);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery(
				"SELECT  ROUND(IFNULL(SUM(TOTAL_PAYMENT),0),3)\n" + "FROM ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayOrderPaymentResult = query.getSingleResult();

		double totalTodayOrderPayment = 0;

		if (totalTodayOrderPaymentResult != null)
			totalTodayOrderPayment = Double.parseDouble("" + totalTodayOrderPaymentResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Today Total Order Payment");
		not2.setEtc("" + totalTodayOrderPayment);
		not2.setMessage("Total payment amount paied to buy goods");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		return notificationDs;

	}

}
