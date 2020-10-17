package xyz.treppi.lobby.scoreboard;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import xyz.treppi.lobby.LobbyPlugin;
import xyz.treppi.lobby.eco.EconomyAPI;

public class ScoreboardController {
    private static final int updateTicks = 20; // one second
    private static final String title = "§e§ltreppi.xyz";
    public static ArrayList<String> getLines() {
        ArrayList<String> lines = new ArrayList<String>();

        lines.add("§ctreppi.xyz");
        lines.add("");
        lines.add("§7xp: §b{xp}");
        lines.add("§7coins: §e{coins}");
        lines.add("§e                  ");
        lines.add("");

        return lines;
    }

    public static void startScoreboard() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyPlugin.getPlugin(), new BukkitRunnable() {

            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {

                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    Scoreboard board = manager.getNewScoreboard();

                    Objective objective = board.registerNewObjective("test", "dummy");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    objective.setDisplayName(title);

                    for(int i = getLines().size() - 1; i >= 0; i--) {
                        String line = replaceLine(getLines().get(i), p);
                        Score score = objective.getScore(line);
                        score.setScore(i);
                    }
                    p.setScoreboard(board);
                }
            }

        }, 0, updateTicks);
    }

    public static String replaceLine(String line, Player p) {
        String uuid = p.getUniqueId().toString();

        line = line.replace("{coins}", Integer.toString(EconomyAPI.getCoins(uuid)));
        line = line.replace("{xp}", Integer.toString(EconomyAPI.getXP(uuid)));

        return line;
    }
}
