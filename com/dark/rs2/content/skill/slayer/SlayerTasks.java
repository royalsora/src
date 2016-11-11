package com.dark.rs2.content.skill.slayer;

public class SlayerTasks {
	
	/* Low level tasks */
public static enum LowLevel {
		
		MAN("Man"),
		COW("Cow"),
		ROCK_CRAB("Rock crab"),
		CHAOS_DRUID("Chaos druid"),
		HILL_GIANT("Hill giant"),
		GIANT_BAT("Giant bat"),
		CRAWLING_HAND("Crawling hand"),
		SKELETON("Skeleton"),
		BLACK_KNIGHT("Black knight"),
		CHAOS_DWARF("Chaos dwarf"),
		MAGIC_AXE("Magic axe"),
		BANSHEE("Banshee");

		public final String name;
		public final byte lvl;

		private LowLevel(String name) {
			this.name = name;
			lvl = SlayerMonsters.getLevelForName(name.toLowerCase());
		}
	}

	/* Medium level tasks */
	public static enum MediumLevel {
		
		BABY_DRAGON("Baby dragon"),
		RED_DRAGON("Red dragon"),
		LESSER_DEMON("Lesser demon"),
		GREEN_DRAGON("Green dragon"),	
		FIRE_GIANT("Fire giant"),
		ABYSSAL_SPAWN("Abyssal spawn"),
		INFERNAL_MAGE("Infernal mage"),
		BLOODVELD("Bloodveld"),
		MOSS_GIANT("Moss giant");

		public final String name;
		public final byte lvl;

		private MediumLevel(String name) {
			this.name = name;
			lvl = SlayerMonsters.getLevelForName(name.toLowerCase());
		}
	}

	/* High level tasks */
	public static enum HighLevel {
		
		ABYSSAL("Abyssal"),
		BLACK_DRAGON("Black dragon"),
		DUST_DEVIL("Dust devil"),
		GARGOYLE("Gargoyle"),
		FIRE_GIANT("Fire giant"),
		LAVA_DRAGON("Lava dragon"),
		SMOKE_DEVIL("Smoke devil"),
		ANKOU("Ankou"),
		WATERFIEND("Waterfiend"),
		ABERRANT_SPECTRE("Aberrant spectre"),
		KALPHITE("Kalphite"),
		HELLHOUND("Hellhound"),
		BLACK_DEMON("Black demon"),
		DARK_BEAST("Dark beast"),
		SKELETAL_WYVERN("Skeletal wyvern"),
		CAVE_KRAKEN("Cave kraken");
		
		public final String name;
		public final byte lvl;
		
		private HighLevel(String name) {
			this.name = name;
			lvl = SlayerMonsters.getLevelForName(name.toLowerCase());
		}
		
	}
	
	/* Boss tasks */
	public static enum BossLevel {
		
		ZULRAH("Zulrah"),
		KING_BLACK_DRAGON("King black dragon"),
		KREE_ARRA("Kree'arra"),
		COMMANDER_ZILYANA("Commander Zilyana"),
		CORPOREAL_BEAST("Corporeal Beast"),
		BARRELCHEST("Barrelchest"),
		KRAKEN("Kraken"),
		GIANT_MOLE("Giant mole"),
		CHAOS_ELEMENTAL("Chaos Elemental"),
		CALLISTO("Callisto"),
		VETION("Vet'ion"),
		CHAOS_FANATIC("Chaos Fanatic"),
		GORILLA("Gorilla"),
		CERBERUS("Cerberus"),
		THERMONUCLEAR_SMOKE_DEVIL("Thermonuclear Smoke Devil"),
		GENERAL_GRAARDOR("General Graardor"),
		DAGANNOTH("Dagannoth");
		
		public final String name;
		public final byte lvl;
		
		private BossLevel(String name) {
			this.name = name;
			lvl = SlayerMonsters.getLevelForName(name.toLowerCase());
		}		
	}
	
}
