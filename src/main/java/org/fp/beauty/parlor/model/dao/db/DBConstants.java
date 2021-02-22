package org.fp.beauty.parlor.model.dao.db;

public class DBConstants {
    private DBConstants() {
    }

    //Client
    //Admin

    //Master
    public static final String INITIALISE_MASTER_RATING = "INSERT INTO b_master_additional_inf (master_id) VALUES (?)";
    public static final String FIND_ALL_MASTERS_WITH_RATING = "SELECT master_id,b_user.en_name , b_user.ua_surname,b_user.ua_name , b_user.en_surname, b_user.email, b_user.password, " +
            "rating  FROM b_user JOIN b_master_additional_inf ON b_user.id = master_id" ;
    public static final String FIND_MASTER_ID_BY_NAME_AND_SURNAME = "SELECT id FROM b_user WHERE u_role = 'master' AND en_name = ? AND en_surname = ? OR u_role = 'master' AND ua_name = ? AND ua_surname = ?";
    public static final String FIND_MASTERS_BY_TREATMENT_NAME_AND_PRICE = "SELECT  b_user.en_name AS en_master_name, b_user.en_surname AS en_master_surname,b_user.ua_name AS ua_master_name, b_user.ua_surname AS ua_master_surname," +
            " b_treatment.en_name AS en_treatment_name,b_treatment.ua_name AS ua_treatment_name,b_treatment.price AS treatment_price " +
            "FROM b_user JOIN b_master_treatment ON b_user.id = b_master_treatment.master_id " +
            "JOIN b_treatment ON b_treatment.id = b_master_treatment.treatment_id WHERE b_treatment.en_name = ? AND b_treatment.price = ? OR  b_treatment.ua_name = ? AND b_treatment.price = ? ";

    //User
    public static final String FIND_USERS_ID_BY_NAME_SURNAME_AND_PASSWORD = "SELECT id FROM b_user WHERE en_name = ? AND en_surname = ? AND password = ? OR  ua_name = ? AND ua_surname = ? AND password = ? ";
    public static final String FIND_ALL_USERS_BY_ROLE = "SELECT * FROM b_user WHERE u_role = ? ";
    public static final String INSERT_USER = "INSERT INTO b_user ( ua_name, ua_surname,en_name,en_surname, email, password, u_role) VALUES ( ?,?,?, ?, ?, ?, ?) ";
    public static final String FIND_ID_BY_USER_EN_NAME_SURNAME_PASSWORD_AND_ROLE ="SELECT id FROM b_user  WHERE en_name = ? AND en_surname = ? AND password = ? AND u_role = ? ";
    //Treatment
    public static final String FIND_ALL_TREATMENTS = "SELECT * FROM b_treatment ";
    public static final String FIND_TREATMENT_ID_BY_NAME_AND_PRICE = "SELECT id FROM b_treatment WHERE en_name = ? AND price = ? OR ua_name = ? AND price = ? ";
    public static final String FIND_TREATMENTS_BY_MASTER_NAME_AND_SURNAME ="SELECT  b_user.en_name AS en_master_name, b_user.en_surname AS en_master_surname, b_treatment.en_name AS en_treatment_name " +
            "b_user.ua_name AS ua_master_name, b_user.ua_surname AS ua_master_surname, b_treatment.ua_name AS ua_treatment_name,b_treatment.price AS treatment_price " +
            " FROM b_user JOIN b_master_treatment ON b_user.id = b_master_treatment.master_id " +
            " JOIN b_treatment ON b_treatment.id = b_master_treatment.treatment_id WHERE b_user.en_name = ? AND b_user.en_surname = ? OR b_user.ua_name = ? AND b_user.ua_surname = ? ";
    //Review
    public static final String INSERT_REVIEW = "INSERT INTO b_review (review_content,date_time, client_id) VALUES (?,?,?)";
    public static final String FIND_REVIEW_ID_BY_CONTENT_AND_CLIENT_ID ="SELECT id FROM b_review WHERE review_content = ? AND client_id = ? ";
    public static final String FIND_NUMBER_OF_REVIEWS_ID ="SELECT COUNT(id) FROM b_review";
    public static final String FIND_ALL_BY_PAGE_AND_NUMBER_OF_RECORDS = "SELECT b_review.id AS review_id,review_content,date_time, b_user.en_name AS en_client_name,b_user.en_surname AS en_client_surname," +
            " b_user.ua_name AS ua_client_name,b_user.ua_surname AS ua_client_surname " +
            " FROM b_review JOIN b_user ON b_review.client_id = b_user.id ORDER BY b_review.date_time DESC  LIMIT ? OFFSET ?";
    public static final String FIND_FIVE_LATEST_REVIEWS = " SELECT b_review.id AS review_id,review_content, b_user.en_name AS en_client_name,date_time, b_user.ua_name AS ua_client_name,b_user.ua_surname AS ua_client_surname,b_user.en_surname AS en_client_surname" +
            " FROM b_review JOIN b_user ON b_review.client_id = b_user.id ORDER BY b_review.date_time DESC LIMIT 5 ";
    //Order
    public static final String FIND_ORDER_ID_BY_DATE_TIME_MASTER_ID_AND_CLIENT_ID = "SELECT id FROM b_order WHERE treatment_execution_date = ? AND treatment_execution_time = ? AND master_id = ? AND client_id = ? ";
    public static final String INSERT_ORDER = "INSERT INTO b_order(treatment_execution_date, treatment_execution_time, status, treatment_id, client_id, master_id) VALUES (?, ?, ?, ?, ?, ?) ";
    public static final String SET_ORDER_TIME_BY_ORDER_ID = "UPDATE b_order SET treatment_execution_time  = ? WHERE id = ?";
    public static final String FIND_ALL_IDS_WHERE_DATE_LOWER_THAN_SOME_DATE_AND_STATUS_IS_NOT_SOME_STATUS ="SELECT id FROM b_order WHERE treatment_execution_date < ? AND status <> ?";
    public static final String FIND_ORDER_ID_BY_MASTER_ID_DATE_AND_TIME = "SELECT id FROM b_order WHERE master_id = ? AND treatment_execution_date = ? AND treatment_execution_time = ? ";
    public static final String FIND_TAKEN_TIME_BY_MASTER_ID_AND_DATE = "SELECT treatment_execution_time FROM  b_order WHERE master_id = ? AND  treatment_execution_date = ? ";
    public static final String FIND_ORDERS_WITH_MASTER_AND_TREATMENT_BY_ORDER_ID = "SELECT b_order.id AS order_id, treatment_execution_date, treatment_execution_time, b_order.status AS order_status,b_user.id AS master_id,b_user.en_name AS en_master_name,b_user.ua_name AS ua_master_name, " +
            "b_user.en_surname AS en_master_surname,b_user.ua_surname AS ua_master_surname,b_treatment.en_name AS en_treatment_name,b_treatment.ua_name AS ua_treatment_name, b_treatment.price AS treatment_price  FROM b_order JOIN b_user ON b_order.master_id = b_user.id " +
            " JOIN b_treatment ON b_treatment.id=b_order.treatment_id WHERE b_order.status <>'done' AND b_order.client_id = ? ORDER BY treatment_execution_date,treatment_execution_time ASC ";
    public static final String SET_ORDER_STATUS_BY_ORDER_ID = "UPDATE b_order SET status = ?  WHERE id = ?";
    public static final String FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT_BY_DATE = "SELECT b_order.id AS order_id, b_order.treatment_execution_date " +
            "AS order_date, b_order.treatment_execution_time AS order_time, status, b_treatment.en_name " +
            "AS en_treatment_name,b_treatment.ua_name AS ua_treatment_name, b_treatment.price AS treatment_price, master.en_name AS en_master_name,master.ua_name AS ua_master_name, " +
            "master.en_surname AS  en_master_surname,master.ua_surname AS  ua_master_surname,client.en_name AS en_client_name, client.ua_name AS ua_client_name," +
            "client.en_surname AS  en_client_surname,client.ua_surname AS  ua_client_surname " +
            "FROM b_order JOIN b_user AS master ON b_order.master_id = master.id " +
            "JOIN b_user AS client ON b_order.client_id = client.id " +
            "JOIN b_treatment ON b_order.treatment_id = b_treatment.id WHERE b_order.treatment_execution_date = ?";
    public static final String FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT_BY_DATE_AND_MASTER_ID_FOR_MASTER = "SELECT b_order.id AS order_id, b_order.treatment_execution_date " +
            "AS order_date, b_order.treatment_execution_time AS order_time, status, b_treatment.en_name " +
            "AS en_treatment_name,b_treatment.ua_name AS ua_treatment_name, b_treatment.price AS treatment_price, master.en_name AS en_master_name,master.ua_name AS ua_master_name, " +
            "master.en_surname AS  en_master_surname,client.en_name AS en_client_name,master.ua_surname AS  ua_master_surname,client.ua_name AS ua_client_name, " +
            "client.en_surname AS  en_client_surname, client.ua_surname AS  ua_client_surname " +
            "FROM b_order JOIN b_user AS master ON b_order.master_id = master.id " +
            "JOIN b_user AS client ON b_order.client_id = client.id " +
            "JOIN b_treatment ON b_order.treatment_id = b_treatment.id WHERE (b_order.status = 'paid' OR b_order.status = 'done') AND   b_order.treatment_execution_date = ? AND b_order.master_id = ? ORDER BY b_order.treatment_execution_time ASC ";
    public static final String FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT = "SELECT b_order.id AS order_id, b_order.treatment_execution_date " +
            "AS order_date, b_order.treatment_execution_time AS order_time, status, b_treatment.en_name " +
            "AS en_treatment_name,b_treatment.ua_name AS ua_treatment_name , b_treatment.price AS treatment_price, master.en_name AS en_master_name,master.ua_name AS ua_master_name, " +
            "master.en_surname AS  en_master_surname,master.ua_surname AS  ua_master_surname,client.en_name AS en_client_name, client.ua_name AS ua_client_name," +
            "client.en_surname AS  en_client_surname,client.ua_surname AS  ua_client_surname " +
            "FROM b_order JOIN b_user AS master ON b_order.master_id = master.id " +
            "JOIN b_user AS client ON b_order.client_id = client.id " +
            "JOIN b_treatment ON b_order.treatment_id = b_treatment.id ";






    public static final String FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT_BY_DATE_AND_MASTER_ID = "SELECT b_order.id AS order_id, b_order.treatment_execution_date " +
            "AS order_date, b_order.treatment_execution_time AS order_time, status, b_treatment.name " +
            "AS treatment_name, b_treatment.price AS treatment_price, master.name AS master_name, " +
            "master.surname AS  master_surname,client.name AS client_name, " +
            "client.surname AS  client_surname " +
            "FROM b_order JOIN b_user AS master ON b_order.master_id = master.id " +
            "JOIN b_user AS client ON b_order.client_id = client.id " +
            "JOIN b_treatment ON b_order.treatment_id = b_treatment.id WHERE b_order.treatment_execution_date = ? AND b_order.master_id = ? ORDER BY b_order.treatment_execution_time ASC ";


    //TODO  sort in the end


    public static final String DELETE_USER = "DELETE FROM b_user WHERE id = ? ";
    public static final String FIND_RATING_BY_ID = "SELECT rating FROM  b_master_additional_inf WHERE master_id = ? ";

    public static final String SET_REQUEST_FEEDBACK_TRUE_BY_DATE = "UPDATE b_order SET request_feedback = true WHERE treatment_execution_date  = ? ";












    public static final String FIND_ALL_ORDERS_BY_CLIENT_ID = "SELECT * FROM b_order WHERE client_id = ? ";
    public static final String FIND_MASTER_BY_ID = "SELECT * FROM b_user WHERE u_role = 'master' AND id = ? ";



    //TODO remove in near future
    public static final String FIND_USER_BY_NAME_AND_SURNAME_AND_ROLE ="SELECT * FROM b_user WHERE name = ? AND surname = ? AND role = ? " ;
    public static final String DELETE_ESSENCE = "DELETE b_user FROM  WHERE id = ? ";
    public static final String FIND_ESSENCE_ID_BY_NAME_SURNAME_AND_PASSWORD ="SELECT id FROM b_user WHERE name = ? AND surname = ? AND password = ? ";
    public static final String INSERT_ESSENCE = "INSERT INTO b_user ( name, surname , email, password,u_role) VALUES ( ? , ? , ? , ? , ? ) ";
}

