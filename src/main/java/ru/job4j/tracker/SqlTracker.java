package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker() {
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement =
                     cn.prepareStatement("insert into items(name) values (?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        int rsl = 0;
        String sql = "update items set name = ? where id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, id);
            rsl = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl > 0;
    }

    @Override
    public boolean delete(int id) {
        int rsl = 0;
        String sql = "delete from items where id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rsl = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl > 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        String sql = "select * from items";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            try (ResultSet rslSet = preparedStatement.executeQuery()) {
                while (rslSet.next()) {
                    rsl.add(new Item(rslSet.getString("name"), rslSet.getInt("id")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        String sql = "select * from items where name = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setString(1, key);
            try (ResultSet rslSet = preparedStatement.executeQuery()) {
                while (rslSet.next()) {
                    rsl.add(new Item(rslSet.getString("name"), rslSet.getInt("id")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        String sql = "select * from items where id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rslSet = preparedStatement.executeQuery()) {
                if (rslSet.next()) {
                    Item rsl = new Item();
                    rsl.setId(rslSet.getInt("id"));
                    rsl.setName(rslSet.getString("name"));
                    return rsl;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Cant execute statement!");
        }
        return null;
    }
}
