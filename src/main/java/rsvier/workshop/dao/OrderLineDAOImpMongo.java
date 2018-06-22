package rsvier.workshop.dao;

import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class OrderLineDAOImpMongo implements OrderLineDAO {

	private DBCollection collection;
	private Logger logger = LogConnection.getLogger();

	public OrderLineDAOImpMongo() {
		try {
			DB db = DatabaseConnectionXML.getConnectionMongoDB();
			collection = db.getCollection("orderLine");
		} catch (Exception e) {
			logger.log(Level.WARNING, "SQL exception occured.Connection with MongoDB failed.", e);
		}

	}

	@Override
	public List<OrderLine> getAllOrderLines() {

		List<OrderLine> orderLineList = new ArrayList<>();

		try (DBCursor cursor = collection.find().skip(1)) {

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(DAOFactory.getProductDAO().getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);
			}
			
			return orderLineList;

		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromProduct(Product product) {

		List<OrderLine> orderLineList = new ArrayList<>();
		DBObject query = new BasicDBObject("product_id", product.getProductId());

		try (DBCursor cursor = collection.find(query)) {

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(DAOFactory.getProductDAO().getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);
			}
			
			return orderLineList;
			
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromOrder(Order order) {

		List<OrderLine> orderLineList = new ArrayList<>();
		DBObject query = new BasicDBObject("order_id", order.getOrderId());

		try (DBCursor cursor = collection.find(query)) {

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(DAOFactory.getProductDAO().getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);
			}
			
			return orderLineList;
			
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public OrderLine getOrderLine(int orderLineId) {

		OrderLine orderLine = null;
		DBObject query = new BasicDBObject("_id", orderLineId);

		try (DBCursor cursor = collection.find(query)) {

			if (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(DAOFactory.getProductDAO().getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				orderLine = orderLineBuilder.build();

			}
			
			return orderLine;
			
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public void createOrderLine(List<OrderLine> orderLines, int orderId) {

		for(OrderLine orderLine : orderLines) {
			
			double generatedIdDouble = (Double) getNextSequence("orderLine_id");
			int generatedIdInteger = (int) generatedIdDouble;
			
		DBObject newOrderLine = new BasicDBObject("_id",generatedIdInteger)
				.append("product_id", orderLine.getProduct().getProductId())
				.append("order_id", orderId)
				.append("number_of_products",orderLine.getNumberOfProducts());
		try {
				collection.insert(newOrderLine);
				logger.log(Level.INFO, "Orderline successfully created.");
				
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
	}
	}
	@Override
	public void createOrderLine(OrderLine orderLine, int orderId) {
		
		double generatedIdDouble = (Double) getNextSequence("orderLine_id");
		int generatedIdInteger = (int) generatedIdDouble;
		
	DBObject newOrderLine = new BasicDBObject("_id",generatedIdInteger)
			.append("order_id", orderId)
			.append("product_id", orderLine.getProduct().getProductId())
			.append("number_of_products",orderLine.getNumberOfProducts());
	
	try {
			collection.insert(newOrderLine);
			logger.log(Level.INFO, "Orderline successfully created.");
			
	} catch (MongoException e) {
		logger.log(Level.WARNING, "SQL exception occured", e);

	}
	}

	@Override
	public void deleteOrderLine(OrderLine orderLine) {
		
		try {
		collection.remove(new BasicDBObject("_id", orderLine.getOrderLineId()));
		logger.log(Level.INFO, "Orderline successfully deleted.");
		
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		
		
		int orderId = 0;
		
		DBObject query = new BasicDBObject("_id", orderLine.getOrderLineId());

		try (DBCursor cursor = collection.find(query)) {

			if (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;
				orderId = orderLineObj.getInt("order_id");
				
			}
		}
		
		

		DBObject updateOrderLine = new BasicDBObject("_id",orderLine.getOrderLineId())
				.append("order_id", orderId)
				.append("product_id", orderLine.getProduct().getProductId())
				.append("number_of_products",orderLine.getNumberOfProducts());
		
	try {
		collection.update(new BasicDBObject("_id",orderLine.getOrderLineId()), updateOrderLine);
		logger.log(Level.INFO, "Orderline successfully updated.");
		
	} catch (MongoException e) {
		logger.log(Level.WARNING, "SQL exception occured", e);

	}
	}


	// Method of auto-increment Id
	public Object getNextSequence(String orderLineId) {

		BasicDBObject find = new BasicDBObject();
		find.put("_id", orderLineId);
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq", 1));
		DBObject obj = collection.findAndModify(find, update);
		// return Object
		return obj.get("seq");
	}

}
