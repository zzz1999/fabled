/**
 * Fabled
 * studio.magemonkey.fabled.api.skills.Skill
 * <p>
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2024 MageMonkeyStudio
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software") to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package studio.magemonkey.fabled.api.skills;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import studio.magemonkey.codex.mccore.config.Filter;
import studio.magemonkey.codex.mccore.config.FilterType;
import studio.magemonkey.codex.mccore.config.parse.DataSection;
import studio.magemonkey.codex.mccore.config.parse.NumberParser;
import studio.magemonkey.codex.mccore.util.TextFormatter;
import studio.magemonkey.codex.registry.damage.DamageRegistry;
import studio.magemonkey.fabled.Fabled;
import studio.magemonkey.fabled.api.ReadOnlySettings;
import studio.magemonkey.fabled.api.Settings;
import studio.magemonkey.fabled.api.event.SkillDamageEvent;
import studio.magemonkey.fabled.api.event.TrueDamageEvent;
import studio.magemonkey.fabled.api.player.PlayerCombos;
import studio.magemonkey.fabled.api.player.PlayerData;
import studio.magemonkey.fabled.api.player.PlayerSkill;
import studio.magemonkey.fabled.api.util.DamageLoreRemover;
import studio.magemonkey.fabled.api.util.Data;
import studio.magemonkey.fabled.data.Permissions;
import studio.magemonkey.fabled.dynamic.TempEntity;
import studio.magemonkey.fabled.gui.tool.IconHolder;
import studio.magemonkey.fabled.hook.NoCheatHook;
import studio.magemonkey.fabled.hook.PluginChecker;
import studio.magemonkey.fabled.language.NotificationNodes;
import studio.magemonkey.fabled.language.RPGFilter;
import studio.magemonkey.fabled.language.SkillNodes;
import studio.magemonkey.fabled.listener.MechanicListener;
import studio.magemonkey.fabled.log.Logger;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Represents a template for a skill used in the RPG system. This is
 * the class to extend when creating your own custom skills.
 */
public abstract class Skill implements IconHolder {
    private static final DecimalFormat     FORMAT           = new DecimalFormat("#########0.0#");
    private static final String            NAME             = "name";
    private static final String            TYPE             = "type";
    private static final String            LAYOUT           = "icon-lore";
    private static final String            MAX              = "max-level";
    private static final String            REQ              = "skill-req";
    private static final String            REQLVL           = "skill-req-lvl";
    private static final String            MSG              = "msg";
    private static final String            PERM             = "needs-permission";
    private static final String            COOLDOWN_MESSAGE = "cooldown-message";
    private static final String            DESC             = "desc";
    private static final String            ATTR             = "attributes";
    private static final String            COMBO            = "combo";
    /**
     * -- GETTER --
     *  Checks whether the current damage event is due to
     *  skills damaging an entity. This method is used by the API
     *  and shouldn't be used by other plugins.
     *
     * @return true if caused by a skill, false otherwise
     */
    @Getter
    private static       boolean           skillDamage      = false;
    /**
     * The settings for the skill which include configurable stats
     * for your mechanics and the defaults such as mana cost, level
     * requirement, skill point cost, and cooldown.
     */
    protected final      Settings          settings         = new Settings();
    private final        ArrayList<String> description      = new ArrayList<>();
    private final        ReadOnlySettings  readOnlySettings = new ReadOnlySettings(settings);
    /**
     * -- GETTER --
     *  Retrieves the configuration key for the skill
     *
     * @return configuration key for the skill
     */
    @Getter
    private final        String            key;
    private              List<String>      iconLore;
    /**
     * -- GETTER --
     *  Retrieves the indicator representing the skill for menus
     *
     * @return indicator for the skill
     */
    @Getter
    private              ItemStack         indicator;
    /**
     * -- GETTER --
     *  Retrieves the name of the skill
     *
     * @return skill name
     */
    @Getter
    private              String            name;
    /**
     * -- GETTER --
     *  Retrieves the descriptive type of the skill
     *
     * @return descriptive type of the skill
     */
    @Getter
    private              String            type;
    /**
     * -- GETTER --
     *  Retrieves the message for the skill to display when cast.
     *
     * @return cast message of the skill
     */
    @Getter
    private              String            message;
    /**
     * -- GETTER --
     *  Retrieves the skill required to be upgraded before this one
     *
     * @return required skill
     */
    @Getter
    private              String            skillReq;
    /**
     * -- GETTER --
     *  Retrieves the max level the skill can reach
     *
     * @return max skill level
     */
    @Getter
    private              int               maxLevel;
    /**
     * -- GETTER --
     *  Retrieves the level of the required skill needed to be obtained
     *  before this one can be upgraded.
     *
     * @return required skill level
     */
    @Getter
    private              int               skillReqLevel;
    private              boolean           needsPermission;
    private              boolean           cooldownMessage;
    /**
     * -- GETTER --
     *  Retrieves the ID of the skill's combo
     *
     * @return combo ID
     * -- SETTER --
     *  Sets the click combo for the skill
     *
     * @param combo new combo
     */
    @Setter
    @Getter
    private              int               combo;

    /**
     * Initializes a new skill that doesn't require any other skill.
     *
     * @param name      name of the skill
     * @param type      descriptive type of the skill
     * @param indicator indicator to represent the skill
     * @param maxLevel  max level the skill can reach
     */
    public Skill(String name, String type, Material indicator, int maxLevel) {
        this(name, type, new ItemStack(indicator), maxLevel, null, 0);
    }

    /**
     * Initializes a skill that requires another skill to be upgraded
     * before it can be upgraded itself.
     *
     * @param name          name of the skill
     * @param type          descriptive type of the skill
     * @param indicator     indicator to represent the skill
     * @param maxLevel      max level the skill can reach
     * @param skillReq      name of the skill required to raise this one
     * @param skillReqLevel level of the required skill needed
     */
    public Skill(String name, String type, Material indicator, int maxLevel, String skillReq, int skillReqLevel) {
        this(name, type, new ItemStack(indicator), maxLevel, skillReq, skillReqLevel);
    }

    /**
     * Initializes a new skill that doesn't require any other skill.
     * The indicator's display name and lore will be used as the layout
     * for the skill tree display.
     *
     * @param name      name of the skill
     * @param type      descriptive type of the skill
     * @param indicator indicator to respresent the skill
     * @param maxLevel  max level the skill can reach
     */
    public Skill(String name, String type, ItemStack indicator, int maxLevel) {
        this(name, type, indicator, maxLevel, null, 0);
    }

    /**
     * Initializes a skill that requires another skill to be upgraded
     * before it can be upgraded itself. The indicator's display name
     * and lore will be used as the layout for the skill tree display.
     *
     * @param name          name of the skill
     * @param type          descriptive type of the skill
     * @param indicator     indicator to represent the skill
     * @param maxLevel      max level the skill can reach
     * @param skillReq      name of the skill required to raise this one
     * @param skillReqLevel level of the required skill needed
     */
    public Skill(String name, String type, ItemStack indicator, int maxLevel, String skillReq, int skillReqLevel) {
        this(name, type, indicator, maxLevel, skillReq, skillReqLevel, null);
    }

    /**
     * Initializes a skill that requires another skill to be upgraded
     * before it can be upgraded itself and enough attributes.
     * The indicator's display name and lore will be used as the layout
     * for the skill tree display.
     *
     * @param name          name of the skill
     * @param type          descriptive type of the skill
     * @param indicator     indicator to represent the skill
     * @param maxLevel      max level the skill can reach
     * @param skillReq      name of the skill required to raise this one
     * @param skillReqLevel level of the required skill needed
     * @param attributes    attributes and their levels required to upgrade skill
     */
    public Skill(String name,
                 String type,
                 ItemStack indicator,
                 int maxLevel,
                 String skillReq,
                 int skillReqLevel,
                 Map<String, Map.Entry<Double, Double>> attributes) {
        if (name == null) {
            throw new IllegalArgumentException("Skill name cannot be null");
        }

        // Default values
        if (type == null) {
            type = "Unknown type";
        }
        if (indicator == null) {
            indicator = new ItemStack(Material.APPLE);
        }
        if (maxLevel < 1) {
            maxLevel = 1;
        }
        if (attributes != null)
            for (Map.Entry<String, Map.Entry<Double, Double>> attribute : attributes.entrySet()) {
                settings.set(attribute.getKey(), attribute.getValue().getKey(), attribute.getValue().getValue());
            }

        this.key = name.toLowerCase();
        this.type = type;
        this.name = name;
        this.indicator = indicator;
        this.maxLevel = maxLevel;
        this.skillReq = skillReq;
        this.skillReqLevel = skillReqLevel;
        this.needsPermission = false;

        this.message = Fabled.getLanguage().getMessage(NotificationNodes.CAST, true, FilterType.COLOR).get(0);
        this.iconLore = Fabled.getLanguage().getMessage(SkillNodes.LAYOUT, true, FilterType.COLOR);
    }

    /**
     * Checks whether the skill has been assigned
     * a click combination.
     *
     * @return true if has a combo, false otherwise
     */
    public boolean hasCombo() {
        return combo >= 0;
    }

    /**
     * Checks whether the skill can automatically
     * level up to the next stage.
     *
     * @param level - the current level of the skill
     * @return true if skill can level up automatically to the next level, false otherwise
     */
    public boolean canAutoLevel(final int level) {
        return getCost(level) == 0 && !doesRequireAttributes(level);
    }

    /**
     * Checks whether the skill has a message to display when cast.
     *
     * @return true if has a message, false otherwise
     */
    public boolean hasMessage() {
        return message != null && message.length() > 0;
    }

    /**
     * Clears the set combo for the skill.
     * Only the API should call this.
     */
    public void clearCombo() {
        combo = -1;
    }

    /**
     * Checks whether the skill needs a permission for a player to use it.
     *
     * @return true if the skill requires a permission to use
     */
    public boolean needsPermission() {
        return needsPermission;
    }

    /**
     * Checks whether the skill requires another before leveling up
     *
     * @return true if the skill requires another skill, false otherwise
     */
    public boolean hasSkillReq() {
        return Fabled.getSkill(skillReq) != null && skillReqLevel > 0;
    }

    /**
     * Retrieves the skill's description
     *
     * @return description of the skill
     */
    public List<String> getDescription() {
        return description;
    }

    /**
     * Retrieves the level requirement for the skill to reach the next level
     *
     * @param level current level of the skill
     * @return level requirement for the next level
     */
    public int getLevelReq(int level) {
        return (int) settings.getAttr(SkillAttribute.LEVEL, level + 1);
    }

    /**
     * Retrieves the mana cost of the skill
     *
     * @param level current level of the skill
     * @return mana cost
     */
    public double getManaCost(int level) {
        return settings.getAttr(SkillAttribute.MANA, level);
    }

    /**
     * Retrieves the cooldown of the skill in seconds
     *
     * @param level current level of the skill
     * @return cooldown
     */
    public double getCooldown(int level) {
        return settings.getAttr(SkillAttribute.COOLDOWN, level);
    }

    /**
     * Checks if skill requires attributes to be upgraded
     *
     * @return true if requires, false otherwise
     */
    private boolean doesRequireAttributes(int level) {
        Set<String> attributes = Fabled.getAttributeManager().getKeys();
        for (String key : attributes) {
            if (settings.getAttr(key, level, 0) != 0) return true;
        }
        return false;
    }

    /**
     * Checks whether a message is sent when attempting to run the skill while in cooldown
     *
     * @return true if the message is sent, false otherwise
     */
    public boolean cooldownMessage() {
        return cooldownMessage;
    }

    /**
     * Retrieves the range of the skill in blocks
     *
     * @param level current level of the skill
     * @return target range
     */
    public double getRange(int level) {
        return settings.getAttr(SkillAttribute.RANGE, level);
    }

    /**
     * Retrieves the skill point cost of the skill
     *
     * @param level current level of the skill
     * @return skill point cost
     */
    public int getCost(int level) {
        return (int) settings.getAttr(SkillAttribute.COST, level + 1);
    }

    /**
     * Retrieves the settings for the skill in a read-only format
     *
     * @return settings for the skill in a read-only format
     */
    public ReadOnlySettings getSettings() {
        return readOnlySettings;
    }

    /**
     * Checks whether this skill can be cast by players
     *
     * @return true if can be cast, false otherwise
     */
    public boolean canCast() {
        return this instanceof SkillShot || this instanceof TargetSkill;
    }

    /**
     * Gets the indicator for the skill for the GUI tools
     *
     * @return GUI tool indicator
     */
    public ItemStack getToolIndicator() {
        ItemStack item = new ItemStack(indicator.getType());
        ItemMeta  meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            if (meta.hasDisplayName())
                lore.add(0, meta.getDisplayName());
            lore.add("Level: " + getLevelReq(0));
            lore.add("Cost: " + getCost(0));
            meta.setLore(lore);
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Fetches the icon for the skill for the player
     *
     * @param data player to get for
     * @return the skill icon
     */
    @Override
    public ItemStack getIcon(PlayerData data) {
        PlayerSkill skill = data.getSkill(name);
        if (skill != null)
            return getIndicator(skill, false);
        else
            return getIndicator();
    }

    @Override
    public boolean isAllowed(final Player player) {
        return !needsPermission()
                || player.hasPermission(Permissions.SKILL)
                || player.hasPermission(Permissions.SKILL + "." + name.toLowerCase().replace(" ", "-"));
    }

    public boolean hasDependency(final PlayerData playerData) {
        // Must meet any skill requirements
        if (getSkillReq() != null) {
            PlayerSkill req = playerData.getSkill(getSkillReq());
            return req == null || req.getLevel() >= getSkillReqLevel();
        }
        return true;
    }

    public boolean isCompatible(final PlayerData playerData) {
        for (final String skillName : settings.getStringList(SkillAttribute.INCOMPATIBLE)) {
            final PlayerSkill skill = playerData.getSkill(skillName);
            if (skill != null && skill.getLevel() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean hasInvestedEnough(final PlayerData playerData) {
        final PlayerSkill skill = playerData.getSkill(name);
        if (skill == null) {
            return false;
        }
        final double reqPoints = settings.getAttr(SkillAttribute.POINTS_SPENT_REQ, skill.getLevel(), 0);
        return playerData.getInvestedSkillPoints() >= reqPoints;
    }

    public boolean hasEnoughAttributes(final PlayerData playerData) {
        Set<String> attributes = Fabled.getAttributeManager().getKeys();
        for (String attr : attributes) {
            if (!checkSingleAttribute(playerData, attr)) return false;
        }
        return true;
    }

    public boolean checkSingleAttribute(final PlayerData playerData, String key) {
        final PlayerSkill skill   = playerData.getSkill(name);
        double            reqAttr = settings.getAttr(key, skill.getLevel() + 1);
        return playerData.getAttribute(key) >= reqAttr;
    }

    /**
     * Retrieves the indicator for the skill while applying filters to match
     * the player-specific data.
     *
     * @param skillData player data
     * @return filtered skill indicator
     */
    public ItemStack getIndicator(PlayerSkill skillData, boolean brief) {
        Player player = skillData.getPlayerData().getPlayer();

        ItemStack item = new ItemStack(indicator.getType());
        item.setAmount(Math.max(1, skillData.getLevel()));
        ItemMeta meta = item.hasItemMeta()
                ? item.getItemMeta()
                : Bukkit.getItemFactory().getItemMeta(item.getType());
        ItemMeta iconMeta = indicator.getItemMeta();

        if (meta instanceof Damageable) {
            ((Damageable) meta).setDamage(((Damageable) iconMeta).getDamage());
        }

        if (iconMeta.hasCustomModelData()) {
            meta.setCustomModelData(iconMeta.getCustomModelData());
        }

        List<String> lore = new ArrayList<>();

        String MET = Fabled.getLanguage().getMessage(SkillNodes.REQUIREMENT_MET, true, FilterType.COLOR).get(0);
        String NOT_MET =
                Fabled.getLanguage().getMessage(SkillNodes.REQUIREMENT_NOT_MET, true, FilterType.COLOR).get(0);
        MET = MET.substring(0, MET.length() - 2);
        NOT_MET = NOT_MET.substring(0, NOT_MET.length() - 2);

        final String lvlReq =
                skillData.getLevelReq() <= skillData.getPlayerClass().getLevel() ? MET : NOT_MET;
        final String costReq =
                skillData.getCost() <= skillData.getPlayerClass().getPoints() ? MET : NOT_MET;
        final String        spentReq             = hasInvestedEnough(skillData.getPlayerData()) ? MET : NOT_MET;
        final String        branchReq            = isCompatible(skillData.getPlayerData()) ? MET : NOT_MET;
        final String        skillReq             = isCompatible(skillData.getPlayerData()) ? MET : NOT_MET;
        final String        attrReq              = hasEnoughAttributes(skillData.getPlayerData()) ? MET : NOT_MET;
        Map<String, String> attributeSpecificReq = new HashMap<>();
        for (String key : Fabled.getAttributeManager().getKeys()) {
            attributeSpecificReq.put(key, checkSingleAttribute(skillData.getPlayerData(), key) ? MET : NOT_MET);
        }

        String attrChanging =
                Fabled.getLanguage().getMessage(SkillNodes.ATTRIBUTE_CHANGING, true, FilterType.COLOR).get(0);
        String attrStatic =
                Fabled.getLanguage().getMessage(SkillNodes.ATTRIBUTE_NOT_CHANGING, true, FilterType.COLOR).get(0);

        for (String line : iconLore) {
            try {
                // General data
                line = line.replace("{level}", "" + skillData.getLevel())
                        .replace("{req:lvl}", lvlReq)
                        .replace("{req:level}", lvlReq)
                        .replace("{req:cost}", costReq)
                        .replace("{req:spent}", spentReq)
                        .replace("{req:branch}", branchReq)
                        .replace("{req:skill}", skillReq)
                        .replace("{req:attribute}", attrReq)
                        .replace("{max}", "" + maxLevel)
                        .replace("{name}", name)
                        .replace("{type}", type)
                        .replace("{skill_points}", String.valueOf(skillData.getPlayerClass().getPoints()));
                for (Map.Entry<String, String> entry : attributeSpecificReq.entrySet()) {
                    line = line.replace("{req:" + entry.getKey() + "}", entry.getValue());
                }

                // Attributes
                while (line.contains("{attr:")) {
                    int    start     = line.indexOf("{attr:");
                    int    end       = line.indexOf("}", start);
                    String attr      = line.substring(start + 6, end);
                    Object currValue = getAttr(player, attr, Math.max(1, skillData.getLevel()));
                    Object nextValue = getAttr(player, attr, Math.min(skillData.getLevel() + 1, maxLevel));
                    if (attr.equals("level") || attr.equals("cost"))
                        currValue = nextValue =
                                (int) Math.floor(NumberParser.parseDouble(nextValue.toString().replace(',', '.')));

                    if (currValue.equals(nextValue) || brief)
                        line = line.replace("{attr:" + attr + "}",
                                attrStatic.replace("{name}", getAttrName(attr))
                                        .replace("{value}", currValue.toString()));
                    else
                        line = line.replace("{attr:" + attr + "}",
                                attrChanging.replace("{name}", getAttrName(attr))
                                        .replace("{value}", currValue.toString())
                                        .replace("{new}", nextValue.toString()));
                }

                // Full description
                if (line.contains("{desc}")) {
                    for (String descLine : description) {
                        lore.add(line.replace("{desc}", descLine));
                    }
                    continue;
                }

                // Description segments
                if (line.contains("{desc:")) {
                    int      start    = line.indexOf("{desc:");
                    int      end      = line.indexOf("}", start);
                    String   lineInfo = line.substring(start + 6, end);
                    String[] split    = lineInfo.contains("-") ? lineInfo.split("-") : new String[]{lineInfo, lineInfo};
                    start = Integer.parseInt(split[0]) - 1;
                    end = (split[1].equals("x") ? description.size() : Integer.parseInt(split[1]));
                    for (int i = start; i < end && i < description.size(); i++) {
                        lore.add(line.replace("{desc:" + lineInfo + "}", description.get(i)));
                    }
                    continue;
                }

                lore.add(line);
            }

            // Lines with invalid filters are ignored
            catch (Exception ex) {
                Logger.invalid(
                        "Skill icon filter for the skill \"" + name + "\" is invalid (Line = \"" + line + "\") - "
                                + ex.getMessage());
            }
        }

        // Click string at the bottom
        if (Fabled.getSettings().isCombosEnabled() && canCast()) {
            PlayerCombos combos = skillData.getPlayerData().getComboData();
            if (combos.hasCombo(this)) lore.addAll(Arrays.asList("", combos.getComboString(this)));
        }

        // Binds
        if (Fabled.getSettings().isShowBinds() && skillData.getBind() != null) {
            lore.add("");
            final String type = TextFormatter.format(skillData.getBind().name().replace("LEGACY_", ""));
            lore.add(Fabled.getSettings().getBindText().replace("{material}", type));
        }

        if (!lore.isEmpty()) meta.setDisplayName(lore.remove(0));

        meta.setLore(lore);
        item.setItemMeta(meta);
        return DamageLoreRemover.removeAttackDmg(item);
    }

    /**
     * Formats an attribute name for applying to the indicator
     *
     * @param key attribute key
     * @return formatted attribute name
     */
    protected String getAttrName(String key) {
        return TextFormatter.format(key);
    }

    /**
     * Retrieves an attribute value for using in the icon lore
     *
     * @param caster owner of the skill
     * @param key    attribute key
     * @param level  skill level
     * @return attribute value
     */
    protected Object getAttr(LivingEntity caster, String key, int level) {
        Object result = settings.getObj(key, level);
        if (result instanceof Double) {
            return format((Double) result);
        }
        return result;
    }

    /**
     * Formats a double value to prevent excessive decimals
     *
     * @param value double value to format
     * @return formatted double value
     */
    protected String format(double value) {
        String result = FORMAT.format(value);
        if (result.endsWith(".0"))
            return result.substring(0, result.length() - 2);
        else
            return result;
    }

    /**
     * Sends the skill message if one is present from the player to entities
     * within the given radius.
     *
     * @param player player to project the message from
     * @param radius radius to include targets of the message
     */
    public void sendMessage(Player player, double radius) {
        if (hasMessage()) {
            radius *= radius;
            Location l = player.getLocation();
            for (Player p : player.getWorld().getPlayers()) {
                if (p.getLocation().distanceSquared(l) < radius) {
                    p.sendMessage(RPGFilter.SKILL.setReplacement(getName())
                            .apply(Filter.PLAYER.setReplacement(player.getName()).apply(message)));
                }
            }
        }
    }

    /**
     * Applies skill damage to the target, launching the skill damage event
     *
     * @param target target to receive the damage
     * @param damage amount of damage to deal
     * @param source source of the damage (skill caster)
     */
    public void damage(LivingEntity target, double damage, LivingEntity source) {
        damage(target, damage, source, "default");
    }

    /**
     * Applies skill damage to the target, launching the skill damage event
     *
     * @param target         target to receive the damage
     * @param damage         amount of damage to deal
     * @param source         source of the damage (skill caster)
     * @param classification type of damage to deal
     */
    public void damage(LivingEntity target, double damage, LivingEntity source, String classification) {
        damage(target, damage, source, classification, true);
    }

    /**
     * Applies skill damage to the target, launching the skill damage event
     *
     * @param target         target to receive the damage
     * @param damage         amount of damage to deal
     * @param source         source of the damage (skill caster)
     * @param classification type of damage to deal
     * @param knockback      whether the damage should apply knockback
     */
    public void damage(LivingEntity target,
                       double damage,
                       LivingEntity source,
                       String classification,
                       boolean knockback) {
        damage(target, damage, source, classification, knockback, EntityDamageEvent.DamageCause.ENTITY_ATTACK);
    }

    /**
     * Applies skill damage to the target, launching the skill damage event
     *
     * @param target         target to receive the damage
     * @param damage         amount of damage to deal
     * @param source         source of the damage (skill caster)
     * @param classification type of damage to deal
     * @param knockback      whether the damage should apply knockback
     * @param cause          the cause of the damage, might affect death messages
     */
    public void damage(LivingEntity target,
                       double damage,
                       LivingEntity source,
                       String classification,
                       boolean knockback,
                       EntityDamageEvent.DamageCause cause) {
        if (target instanceof TempEntity) {
            return;
        }
        if (target.equals(source)) knockback = false;

        // We have to check if the damage event would get cancelled, since we aren't _really_ calling
        // EntityDamageByEntityEvent unless we use knockback, but it may cause multiple call for
        // EntityDamageByEntityEvent
        if (!Fabled.getSettings().canAttack(source, target, cause)) {
            return;
        }

        SkillDamageEvent event = new SkillDamageEvent(this, source, target, damage, classification, knockback);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }

        damage = event.getDamage();
        knockback = event.isKnockback();
        target.setMetadata(MechanicListener.DAMAGE_CAUSE, new FixedMetadataValue((JavaPlugin) Fabled.inst(), cause));
        if (source instanceof Player) {
            if (PluginChecker.isNoCheatActive()) NoCheatHook.exempt((Player) source);
        }

        // Allow damage to occur
        int ticks = target.getNoDamageTicks();
        target.setNoDamageTicks(0);
        skillDamage = true;

        if (knockback) {
            if (!DamageRegistry.dealDamage(target, damage, classification, source))
                target.damage(damage, source);
        } else {
            Vector velocity = target.getVelocity();
            if (!DamageRegistry.dealDamage(target, damage, classification, source))
                target.damage(damage, source);
            target.setVelocity(velocity);
        }


        // Reset damage timer to before the damage was applied
        target.setNoDamageTicks(ticks);
        if (source instanceof Player) {
            if (PluginChecker.isNoCheatActive()) NoCheatHook.unexempt((Player) source);
        }
        skillDamage = false;
    }

    /**
     * Applies skill damage to the target, launching the skill damage event
     * and keeping the damage version compatible.
     *
     * @param target target to receive the damage
     * @param damage amount of damage to deal
     * @param source source of the damage (skill caster)
     */
    public void trueDamage(LivingEntity target, double damage, LivingEntity source) {
        if (target instanceof TempEntity) return;

        TrueDamageEvent event = new TrueDamageEvent(this, source, target, damage);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled() && event.getDamage() != 0) {
            target.setHealth(Math.max(Math.min(target.getHealth() - event.getDamage(),
                    target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()), 0));
        }
    }

    /**
     * Starts the skill's preview effects.
     * Removal of any registered listeners, tasks, entities, or other
     * temporary effects should be included in a {@link Runnable}
     * passed to {@link PlayerData#setOnPreviewStop(Runnable)}
     */
    public void playPreview(PlayerData playerData, int level) {}

    /**
     * Saves the skill data to the configuration, overwriting all previous data
     *
     * @param config config to save to
     */
    public void save(DataSection config) {
        config.set(NAME, name);
        config.set(TYPE, type.replace(ChatColor.COLOR_CHAR, '&'));
        config.set(MAX, maxLevel);
        config.set(REQ, skillReq);
        config.set(REQLVL, skillReqLevel);
        config.set(PERM, needsPermission);
        config.set(COOLDOWN_MESSAGE, cooldownMessage);
        if (combo >= 0 && canCast())
            config.set(COMBO, Fabled.getComboManager().getSaveString(combo));
        settings.save(config.createSection(ATTR));
        if (hasMessage()) {
            config.set(MSG, message.replace(ChatColor.COLOR_CHAR, '&'));
        }
        Data.serializeIcon(indicator, config);
        config.set(DESC, description);
    }

    /**
     * Saves some skill data to the config, avoiding
     * overwriting any pre-existing data
     *
     * @param config config to save to
     */
    public void softSave(DataSection config) {

        boolean neededOnly = !config.keys().isEmpty();
        if (!neededOnly) {
            save(config);
        }
    }

    /**
     * Loads skill data from the configuration
     *
     * @param config config to load from
     */
    public void load(DataSection config) {
        name = config.getString(NAME, name);
        type = TextFormatter.colorString(config.getString(TYPE, name));
        indicator = Data.parseIcon(config);
        maxLevel = config.getInt(MAX, maxLevel);
        skillReq = config.getString(REQ);
        if (skillReq == null || skillReq.isEmpty()) skillReq = null;
        skillReqLevel = config.getInt(REQLVL, skillReqLevel);
        message = TextFormatter.colorString(config.getString(MSG, message));
        needsPermission = config.getString(PERM, needsPermission + "").equalsIgnoreCase("true");
        cooldownMessage = config.getBoolean(COOLDOWN_MESSAGE, true);
        combo = Fabled.getComboManager().parseCombo(config.getString(COMBO));

        if (config.isList(DESC)) {
            description.clear();
            description.addAll(config.getList(DESC));
        }
        if (config.isList(LAYOUT)) {
            iconLore = TextFormatter.colorStringList(config.getList(LAYOUT));
        }

        settings.load(config.getSection(ATTR));
    }
}
