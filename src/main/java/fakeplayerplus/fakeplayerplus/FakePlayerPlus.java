package fakeplayerplus.fakeplayerplus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FakePlayerPlus implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fakeplayer")) {
            if (args.length == 0) {
                // Display help message
                sender.sendMessage(ChatColor.GREEN + "=== FakePlayer Help ===");
                sender.sendMessage(ChatColor.YELLOW + "/fakeplayer create [name] [x] [y] [z] [health]");
                sender.sendMessage(ChatColor.YELLOW + "/fakeplayer remove [name]");
                return true;
            } else if (args[0].equalsIgnoreCase("create") && args.length == 6) {
                // Create fake player entity
                String name = args[1];
                double x = Double.parseDouble(args[2]);
                double y = Double.parseDouble(args[3]);
                double z = Double.parseDouble(args[4]);
                float health = Float.parseFloat(args[5]);

                // Check if player with that name already exists
                if (Bukkit.getPlayer(name) != null) {
                    sender.sendMessage(ChatColor.RED + "A player with that name already exists.");
                    return true;
                }

                // Create new player entity
                Player fakePlayer = (Player) Bukkit.getWorlds().get(0).spawnEntity(new Location(Bukkit.getWorlds().get(0), x, y, z), EntityType.PLAYER);
                fakePlayer.setCustomName(name);
                fakePlayer.setCustomNameVisible(true);
                fakePlayer.setHealth(health);

                sender.sendMessage(ChatColor.GREEN + "Created fake player " + name + ".");
                return true;
            } else if (args[0].equalsIgnoreCase("remove") && args.length == 2) {
                // Remove fake player entity
                String name = args[1];
                Player fakePlayer = Bukkit.getPlayer(name);
                if (fakePlayer == null) {
                    sender.sendMessage(ChatColor.RED + "Could not find fake player " + name + ".");
                    return true;
                }
                fakePlayer.remove();
                sender.sendMessage(ChatColor.GREEN + "Removed fake player " + name + ".");
                return true;
            }
        }
        return false;
    }
}

//created By Kasunhapangama  Contact me in discord KASUN#8971