package com.web.cms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

public class TaskManagerService implements DaoInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManagerService.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     * constructor set data source in constructor
     *
     * @param dataSource
     *
     *
     *
     */
    public TaskManagerService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    /**
     * Method return data source object
     *
     * @return DataSource object
     *
     *
     */
    public DataSource getDataSource() {
        if (this.dataSource != null) {
            return this.dataSource;
        } else {
            return null;
        }
    }

    public TaskManagerService() {

    }

    @Transactional
    @Override
    public void addTask(final Contact task) {
        String query = "insert into contact(first_name,last_name,email,mobile) values (?,?,?,?)";
        Boolean b = jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1, task.getFirst_name());
                ps.setString(2, task.getLast_name());
                ps.setString(3, task.getEmail());
                ps.setString(4, task.getMobile_number());
                return ps.execute();

            }
        });
    //return true;
    }

    /**
     * only admin can delete the contact
     *
     * @param taskId******
     */
    @Secured("ROLE_ADMIN")
    @Transactional
    @Override
    public void archiveTask(final int taskId) {

        String query = "delete  from contact where id=?";
        Boolean b = jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setInt(1, taskId);

                return ps.execute();

            }
        });

    }

    /**
     * *update the contact by i
     *
     * @param task***
     */
    @Transactional
    @Override
    public void updateTask(final Contact task) {

        String query = "update contact set first_name=?, last_name=?, email=?,mobile=?"
                + "where id=?";
        Boolean b = jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1, task.getFirst_name());
                ps.setString(2, task.getLast_name());
                ps.setString(3, task.getEmail());
                ps.setString(4, task.getMobile_number());
                ps.setInt(5, task.getId());
                return ps.execute();

            }
        });

    }

    /**
     * *get all contact
     *
     * @return  **
     */
    @Transactional
    @Override
    public List<Contact> getAllTasks() {
        String sql = "SELECT * FROM contact";
        final List<Contact> tasks = new ArrayList<>();
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<List<Contact>>() {
            @Override
            public List<Contact> doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Contact task = new Contact();
                        task.setId(rs.getInt("id"));
                        task.setFirst_name(rs.getString("first_name"));
                        task.setLast_name(rs.getString("last_name"));
                        task.setEmail(rs.getString("email"));
                        task.setMobile_number(rs.getString("mobile"));
                        tasks.add(task);

                    }
                }
                ps.close();
                return tasks;

            }
        });
    }

    /**
     * *get all contact by i
     *
     * @param taskId
     *
     * @return *
     */
    @Transactional
    @Override
    public List<Contact> getTaskById(final int taskId) {
        String sql = "SELECT * FROM contact WHERE Id = ?";
        final List<Contact> tasks = new ArrayList<>();
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<List<Contact>>() {
            @Override
            public List<Contact> doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setInt(1, taskId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Contact task = new Contact();
                        task.setId(rs.getInt("id"));
                        task.setFirst_name(rs.getString("first_name"));
                        task.setLast_name(rs.getString("last_name"));
                        task.setEmail(rs.getString("email"));
                        task.setMobile_number(rs.getString("mobile"));
                        tasks.add(task);

                    }
                }
                ps.close();
                return tasks;

            }
        });

    }

}
