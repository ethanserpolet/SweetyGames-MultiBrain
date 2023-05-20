package fun.slowfeew.multibrain.SQL;

import fun.slowfeew.multibrain.SQL.config.DatabaseManager;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountPlayer {

    public static boolean hasAccountName(String target, Player sender) {
        try {
            Connection connection = DatabaseManager.Osaka2.getDatabaseAccess().getConnection();
            

            PreparedStatement ps = connection.prepareStatement("SELECT `Player` FROM `General` WHERE `Player` = '" + target + "'");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                connection.close();
                ps.close();
                rs.close();
                return true;
            } else {
                connection.close();
                ps.close();
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean hasAccount(UUID uuid) {
        try {
            Connection connection = DatabaseManager.Osaka2.getDatabaseAccess().getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT `Player` FROM `General` WHERE `UUID` = ?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                connection.close();
                ps.close();
                rs.close();
                return true;
            } else {
                connection.close();
                ps.close();
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("TON CHECK PUE LA MERDE");
        }
        return false;
    }
}
