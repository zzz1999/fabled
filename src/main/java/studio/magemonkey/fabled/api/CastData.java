package studio.magemonkey.fabled.api;

import studio.magemonkey.codex.mccore.util.MobManager;
import studio.magemonkey.fabled.Fabled;
import studio.magemonkey.fabled.api.player.PlayerData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CastData {
    private final Map<String, Object> data = new HashMap<>();

    public CastData(LivingEntity caster) {
        data.put("caster", caster);

        // Load the player's attributes as attr:<attribute> into the castData map
        if (caster instanceof Player) {
            for (String attr : Fabled.getAttributeManager().getAttributes().keySet()) {
                data.put("attr:" + attr.toLowerCase(), (PlayerDataConsumer) () -> {
                    PlayerData playerData = Fabled.getData((Player) caster);
                    int        stage      = playerData.getInvestedAttributeStage(attr);
                    return stage;
                });
            }
        }
    }

    public boolean contains(String key) {
        return data.containsKey(key) && data.get(key) != null;
    }

    public Object getRaw(String key) {
        return data.get(key);
    }

    public double getDouble(String key) {
        double value = 0d;
        Object raw   = getRaw(key);
        if (raw instanceof PlayerDataConsumer) {
            raw = ((PlayerDataConsumer) raw).consume();
        }

        try {
            value = Double.parseDouble(raw.toString());
        } catch (Exception ex) {
            Fabled.inst().getLogger().warning("Could not convert " + raw + " to double.");
        }

        return value;
    }

    public String get(String key) {
        if (!contains(key)) return null;

        Object obj = data.get(key);
        if (obj instanceof Player) {
            obj = ((Player) obj).getName();
        } else if (obj instanceof LivingEntity) {
            obj = MobManager.getName((LivingEntity) obj);
        } else if (obj instanceof PlayerDataConsumer) {
            obj = ((PlayerDataConsumer) obj).consume();
        }

        return obj.toString();
    }

    public String getOrDefault(String key, String def) {
        String val = get(key);
        if (val != null) return val;

        return def;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public void remove(String key) {
        data.remove(key);
    }
}
