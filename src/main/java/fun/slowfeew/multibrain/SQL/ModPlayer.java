package fun.slowfeew.multibrain.SQL;

import fun.slowfeew.multibrain.SQL.config.DatabaseManager;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModPlayer {

    //getModPlayer
    public static boolean getModPlayer(Player p) {

        try {
            Connection connection = DatabaseManager.Osaka2.getDatabaseAccess().getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT `ENABLED` FROM `Mod` WHERE `Player` = ?");
            ps.setString(1, p.getName());

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
            return false;
        }
    }
    //setModPlayer

}
