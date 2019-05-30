//package com.example.demo.Model;
//
//package com.example.cloudsql;
//
//import com.example.demo.Account;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//
//@WebServlet(name = "Index", value = "")
//public class IndexServlet extends HttpServlet {
//
//	private static final Logger LOGGER = Logger.getLogger(IndexServlet.class.getName());
//
//	@Override
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException, ServletException {
//		// Extract the pool from the Servlet Context, reusing the one that was created
//		// in the ContextListener when the application was started
//		DataSource pool = (DataSource) req.getServletContext().getAttribute("my-pool");
//
//		int number;
//		int state;
//		List<Account> accounts = new ArrayList<>();
//		try (Connection conn = pool.getConnection()) {
//			// PreparedStatements are compiled by the database immediately and executed at a later date.
//			// Most databases cache previously compiled queries, which improves efficiency.
//			PreparedStatement getState =  conn.prepareStatement(
//					"SELECT U_State FROM OtpState WHERE U_ID like ?");
//			getState.setString(1, "%" + number + "%");
//			// TODO set Number & State
//			// Execute the statement
//			ResultSet stateResults = getState.executeQuery();
//			// Convert a ResultSet into Vote objects
//			while (stateResults.next()) {
//				String candidate = stateResults.getString(1);
//				Timestamp timeCast = stateResults.getTimestamp(2);
//				recentVotes.add(new Vote(candidate, timeCast));
//			}
//
//		} catch (SQLException ex) {
//			// If something goes wrong, the application needs to react appropriately. This might mean
//			// getting a new connection and executing the query again, or it might mean redirecting the
//			// user to a different page to let them know something went wrong.
//			throw new ServletException("Unable to successfully connect to the database. Please check the "
//					+ "steps in the README and try again.", ex);
//		}
//
//	}
//
//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException {
//
//		// Reuse the pool that was created in the ContextListener when the Servlet started.
//		DataSource pool = (DataSource) req.getServletContext().getAttribute("my-pool");
//		// [START cloud_sql_mysql_servlet_connection]
//		// Using a try-with-resources statement ensures that the connection is always released back
//		// into the pool at the end of the statement (even if an error occurs)
//		try (Connection conn = pool.getConnection()) {
//
//			// PreparedStatements can be more efficient and project against injections.
//			PreparedStatement setState = conn.prepareStatement(
//					"INSERT INTO OtpState (U_ID, U_State) VALUES (?, ?);");
//			setState.setString(1, number + state );
//			setState.setString(1, state);
//			// TODO Set NUMBER & state
//			// Finally, execute the statement. If it fails, an error will be thrown.
//			setState.execute();
//
//		} catch (SQLException ex) {
//			// If something goes wrong, handle the error in this section. This might involve retrying or
//			// adjusting parameters depending on the situation.
//			// [START_EXCLUDE]
//			LOGGER.log(Level.WARNING, "Error while attempting to append the otp state", ex);
//			resp.setStatus(500);
//			resp.getWriter().write("Unable to append this otp state, Please check the application "
//					+ "logs for more details.");
//			// [END_EXCLUDE]
//		}
//		// [END cloud_sql_mysql_servlet_connection]
//
//		resp.setStatus(200);
//		resp.getWriter().printf("Vote successfully cast for '%s' at time %s!\n", team, now);
//	}
//
//}
