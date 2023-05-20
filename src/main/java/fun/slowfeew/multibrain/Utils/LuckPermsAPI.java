package fun.slowfeew.multibrain.Utils;

import com.sun.corba.se.spi.ior.ObjectKey;
import fun.slowfeew.multibrain.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LuckPermsAPI {

    private Main main;


    public LuckPermsAPI(Main main) {
        this.main = main;
    }
    public static String getPrefix(Player p) {
        // Get the LuckPerms API
        LuckPerms api = LuckPermsProvider.get();



        User user = Objects.requireNonNull(api.getUserManager().getUser(p.getUniqueId()));

        // Ensure user is not null
            // Get the user's prefix
            CachedMetaData metaData = user.getCachedData().getMetaData();
            if(metaData.getPrefix() != null) {
                return metaData.getPrefix().replaceAll("&", "§");
            }
            return "null";


    }
}
