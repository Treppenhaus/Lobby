package xyz.treppi.lobby.eco;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import xyz.treppi.lobby.LobbyPlugin;

import java.util.Random;

public class RandomCoindrop implements Listener {

    private static final int delay = 20*60;
    private static final int diameter = 20;

    @EventHandler
    public void onCoinPickup(PlayerPickupItemEvent e) {
        ItemStack item = e.getItem().getItemStack();
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        if(item.getType() == Material.GOLD_NUGGET) {
            e.setCancelled(true);
            e.getItem().remove();

            ItemMeta meta = item.getItemMeta();
            String name = meta.getDisplayName();
            int worth = Integer.parseInt(name);

            p.sendMessage("§2>> §aYou have found coins worth §e$"+worth);

            int balance = EconomyAPI.getCoins(uuid);
            EconomyAPI.setCoins(uuid, balance + worth);
        }
    }

    private static void dropCoinAt(Location location) {
        int worth = getRandomNumberInRange(1, 20);
        World world = location.getWorld();

        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = coin.getItemMeta();

        meta.setDisplayName(Integer.toString(worth));
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        coin.setItemMeta(meta);

        world.dropItemNaturally(location, coin);
    }

    public static void startCoindropper() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyPlugin.getPlugin(), new BukkitRunnable() {

            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    int random = getRandomNumberInRange(0, 10);
                    if(random > 6) {

                        int x = getRandomNumberInRange(0, diameter) - diameter/2;
                        int z = getRandomNumberInRange(0, diameter) - diameter/2;

                        Location location = p.getLocation();
                        Location offset = new Location(location.getWorld(), location.getX()+x, location.getY()+5, location.getZ()+z);

                        dropCoinAt(offset);

                    }
                }
            }

        }, 0, delay);
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
