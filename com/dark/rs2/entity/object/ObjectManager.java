package com.dark.rs2.entity.object;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import com.dark.core.cache.map.MapLoading;
import com.dark.core.cache.map.RSObject;
import com.dark.core.cache.map.Region;
import com.dark.rs2.content.skill.firemaking.FireColor;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;

@SuppressWarnings("all")
public class ObjectManager {
//k
	public static final int BLANK_OBJECT_ID = 2376;

	private static final List<GameObject> active = new LinkedList<GameObject>();
	private static final Deque<GameObject> register = new LinkedList<GameObject>();

	private static final Queue<GameObject> send = new ConcurrentLinkedQueue<GameObject>();

	public static void add(GameObject o) {
		active.add(o);
	}

	public static void addClippedObject(GameObject o) {
		register.add(o);
	}

	public static void declare() {

		for (GameObject i : active) {
			send(getBlankObject(i.getLocation()));
		}

		active.clear();
		
		/** Home Area */
    
		/** Home Area */
		spawnWithObject(410, 3444, 2898, 0, 10, 0);//Lunar Altar
		spawnWithObject(6552, 3437, 2891, 0, 10, 3);//Ancient Altar
		spawnWithObject(409, 3090, 3508, 0, 10, 3);//Altar at edge	
		spawnWithObject(409, 3439, 2898, 0, 10, 2);//Altar	
		spawnWithObject(26254, 3410, 2894, 0, 10, 0);//depoist
		//spawnWithObject(4875, 3422, 2914, 0, 10, 5);//Food stall
		//spawnWithObject(4876, 3095, 3500, 0, 10, 5);//General stall
		//spawnWithObject(4874, 3096, 3500, 0, 10, 5);//Crafting stall
		//spawnWithObject(4877, 3097, 3500, 0, 10, 5);//Magic stall
		//spawnWithObject(4878, 3098, 3500, 0, 10, 5);//Scimitar stall
		spawnWithObject(2191, 3437, 2887, 0, 10, 3);//Crystal chest
		spawnWithObject(4277, 3430, 2919, 0, 10, 2);//fish stall
		spawnWithObject(4278, 3431, 2910, 0, 10, 3);//fur stall
		spawnWithObject(26796, 3429, 2889, 0, 10, 3);//vote booth
		spawnWithObject(3757, 2526, 3867, 0, 10, 3);//dzone cave
		spawnWithObject(11748, 3186, 3436, 0, 10, 2);//varrock bank
		spawnWithObject(10517, 3412, 2912, 0, 10, 0);//Banks				
		spawnWithObject(10517, 3413, 2912, 0, 10, 0);//Banks	
		spawnWithObject(10517, 3414, 2912, 0, 10, 0);//Banks
		spawnWithObject(26194, 3153, 3923, 0, 10, 3);//lever
		spawnWithObject(202, 2606, 3218, 0, 10, 3);//candles
		spawnWithObject(28861, 2606, 3207, 0, 10, 0);//bank chest
		spawnWithObject(28861, 2676, 3378, 0, 10, 0);//bank chest

		spawnWithObject(28861, 1593, 3480, 0, 10, 0);//bank chest
		spawnWithObject(28861, 1651, 3501, 0, 10, 0);//bank chest
		spawnWithObject(28861, 1650, 3501, 0, 10, 0);//bank chest

		spawnWithObject(27014, 2986, 4751, 0, 10, 0);//tendrils	
		spawnWithObject(27014, 2986, 4750, 0, 10, 0);//tendrils	
		spawnWithObject(27014, 2986, 4749, 0, 10, 0);//tendrils	
		deleteWithObject(3078, 3510, 0);
		deleteWithObject(3080, 3510, 0);
		deleteWithObject(3412, 2913, 0);
		deleteWithObject(3081, 3510, 0);

		deleteWithObject(2543, 3867, 0);
		deleteWithObject(2545, 3865, 0);
		deleteWithObject(2541, 3869, 0);
		deleteWithObject(2546, 3869, 0);


		deleteWithObject(2804, 4720, 0);
		deleteWithObject(2800, 4720, 0);
		deleteWithObject(2797, 4717, 0);
		deleteWithObject(2806, 4713, 0);




		deleteWithObject(2420, 9750, 0);
		deleteWithObject(2427, 9747, 0);
		deleteWithObject(2418, 9742, 0);
		deleteWithObject(2388, 9740, 0);
		deleteWithObject(2380, 9750, 0);

		deleteWithObject(3104, 3497, 0);
		deleteWithObject(3105, 3497, 0);
		deleteWithObject(3106, 3497, 0);
		/** Rune ores at mining */
		spawnWithObject(7494, 3051, 9765, 0, 10, 3);
		spawnWithObject(7494, 3052, 9766, 0, 10, 3);
		delete(3104, 3497, 0);
		delete(3105, 3497, 0);
		delete(3106, 3497, 0);

		/* Monkey */
		spawnWithObject(18968, 2715, 9167, 1, 10, 0);//monkey barrier

		spawnWithObject(18968, 2719, 9168, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9169, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9170, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9171, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9172, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9173, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9174, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9175, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9176, 1, 10, 0);//monkey barrier

		spawnWithObject(18968, 2711, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2712, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2713, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2714, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2715, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2716, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2717, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2718, 9176, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2719, 9176, 1, 10, 0);//monkey barrier

		spawnWithObject(18968, 2711, 9168, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9169, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9170, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9171, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9172, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9173, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9174, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9175, 1, 10, 0);//monkey barrier
		spawnWithObject(18968, 2711, 9176, 1, 10, 0);//monkey barrier



		spawnWithObject(26377, 2913, 5300, 2, 10, 2);//godwars
		/* Membership Area */
		deleteWithObject(2822, 3356, 0);
		deleteWithObject(2822, 3355, 0);
		deleteWithObject(2822, 3351, 0);
		deleteWithObject(2822, 3350, 0);
		deleteWithObject(2818, 3351, 0);
		deleteWithObject(2818, 3355, 0);
		deleteWithObject(2817, 3355, 0);
		deleteWithObject(2816, 3354, 0);
		deleteWithObject(2818, 3356, 0);
		deleteWithObject(2816, 3352, 0);
		deleteWithObject(2817, 3353, 0);
		deleteWithObject(2816, 3351, 0);
		deleteWithObject(2821, 3357, 0);
		deleteWithObject(2822, 3360, 0);
		deleteWithObject(2822, 3361, 0);
		deleteWithObject(2821, 3360, 0);
		deleteWithObject(2821, 3361, 0);
		deleteWithObject(2820, 3360, 0);
		deleteWithObject(2820, 3361, 0);	
		deleteWithObject(2819, 3360, 0);
		deleteWithObject(2819, 3361, 0);
		deleteWithObject(2818, 3360, 0);
		deleteWithObject(2818, 3361, 0);
		deleteWithObject(2817, 3360, 0);
		deleteWithObject(2817, 3361, 0);
		deleteWithObject(2817, 3359, 0);
		deleteWithObject(2817, 3358, 0);
		deleteWithObject(2817, 3357, 0);
		deleteWithObject(2857, 3338, 0);
		deleteWithObject(2859, 3338, 0);
		deleteWithObject(2860, 3338, 0);
		deleteWithObject(2862, 3338, 0);
		deleteWithObject(2861, 3335, 0);
		deleteWithObject(2862, 3335, 0);	
		deleteWithObject(2844, 3333, 0);
		deleteWithObject(2845, 3337, 0);
		deleteWithObject(2844, 3337, 0);
		deleteWithObject(2845, 3338, 0);
		deleteWithObject(2844, 3338, 0);
		deleteWithObject(2853, 3355, 0);
		deleteWithObject(2853, 3353, 0);
		deleteWithObject(2849, 3353, 0);
		deleteWithObject(2849, 3354, 0);
		deleteWithObject(2849, 3355, 0);
		deleteWithObject(2851, 3353, 0);
		deleteWithObject(2809, 3341, 0);
		deleteWithObject(2812, 3341, 0);
		deleteWithObject(2812, 3343, 0);
		deleteWithObject(2810, 3342, 0);
		deleteWithObject(2808, 3343, 0);
		deleteWithObject(2808, 3346, 0);
		deleteWithObject(2809, 3346, 0);
		deleteWithObject(2810, 3346, 0);
		deleteWithObject(2812, 3346, 0);
		deleteWithObject(2807, 3354, 0);
		deleteWithObject(2807, 3355, 0);
		deleteWithObject(2806, 3355, 0);
		deleteWithObject(2806, 3356, 0);
		deleteWithObject(2807, 3356, 0);
		deleteWithObject(2808, 3356, 0);				
		deleteWithObject(2830, 3350, 0);
		deleteWithObject(2831, 3348, 0);
		deleteWithObject(2830, 3349, 0);
		deleteWithObject(2816, 3361, 0);	
		deleteWithObject(2812, 3364, 0);
		deleteWithObject(2814, 3364, 0);
		deleteWithObject(2816, 3363, 0);
		deleteWithObject(2818, 3363, 0);
		deleteWithObject(2819, 3362, 0);	
		deleteWithObject(2815, 3358, 0);
		deleteWithObject(2814, 3357, 0);	
		deleteWithObject(2835, 3355, 0);	
		deleteWithObject(3430, 2892, 0);	
		deleteWithObject(3430, 2891, 0);
		deleteWithObject(3431, 2892, 0);	
		deleteWithObject(3431, 2891, 0);
		deleteWithObject(3427, 2923, 0);	
		deleteWithObject(3426, 2923, 0);	
		deleteWithObject(2550, 3896, 0);
		deleteWithObject(2548, 3897, 0);	
		deleteWithObject(2552, 3896, 0);
		deleteWithObject(2551, 3898, 0);
		spawnWithObject(10517, 2816, 3358, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3357, 0, 10, 3);//Banks	
		spawnWithObject(10517, 2810, 3343, 0, 10, 3);//Banks
		spawnWithObject(10517, 2874, 3339, 0, 10, 3);//Banks
		spawnWithObject(10517, 2874, 3340, 0, 10, 3);//Banks
		spawnWithObject(10517, 2829, 3351, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3356, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3355, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3354, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3353, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3352, 0, 10, 3);//Banks
		spawnWithObject(10517, 2816, 3351, 0, 10, 3);//Banks		
		spawnWithObject(10517, 2809, 3347, 0, 10, 0);//Banks				
		spawnWithObject(10517, 2827, 3355, 0, 10, 0);//Banks		
		spawnWithObject(9472, 2818, 3351, 0, 10, 5);//Shop Exchange
		spawnWithObject(10517, 2857, 3338, 0, 10, 0);//Banks
		spawnWithObject(4875, 2863, 3338, 0, 10, 5);//Food stall
		spawnWithObject(4876, 2862, 3338, 0, 10, 5);//General stall
		spawnWithObject(4874, 2861, 3338, 0, 10, 5);//Crafting stall
		spawnWithObject(4877, 2860, 3338, 0, 10, 5);//Magic stall
		spawnWithObject(4878, 2859, 3338, 0, 10, 5);//Scmitar stall
		spawnWithObject(26181, 2874, 3333, 0, 10, 0);//Range
		spawnWithObject(4309, 2847, 3333, 0, 10, 2);//Spinning wheel
		spawnWithObject(11601, 2845, 3333, 0, 10, 3);//Pottery
		spawnWithObject(22472, 2844, 3338, 0, 10, 2);//Tab creation
		spawnWithObject(13618, 2850, 3355, 0, 10, 0);//Wyvern teleport
		spawnWithObject(13619, 2853, 3353, 0, 10, 1);//Fountain of rune teleport
		spawnWithObject(2191, 2818, 3356, 0, 10, 4);//Crystal chest
		spawnWithObject(18772, 1806, 3784, 0, 10, 2);//MysteryBox chest
		spawnWithObject(2097, 2830, 3349, 0, 10, 1);//Anvil
		spawnWithObject(11764, 2811, 3361, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2810, 3359, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2815, 3361, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2815, 3359, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2812, 3364, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2814, 3364, 0, 10, 1);//Magic Tree
		spawnWithObject(11758, 2809, 3356, 0, 10, 1);//Yew Tree
		spawnWithObject(11758, 2809, 3353, 0, 10, 1);//Yew Tree
		spawnWithObject(11758, 2809, 3350, 0, 10, 1);//Yew Tree
		spawnWithObject(11762, 2804, 3344, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2804, 3346, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2806, 3348, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2806, 3351, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2805, 3353, 0, 10, 1);//Maple Tree
		spawnWithObject(7494, 2824, 3359, 0, 10, 1);//Rune Ore
		spawnWithObject(7494, 2824, 3358, 0, 10, 1);//Rune Ore
		spawnWithObject(7494, 2824, 3357, 0, 10, 1);//Rune Ore
		spawnWithObject(7494, 2826, 3359, 0, 10, 1);//Rune Ore
		spawnWithObject(7494, 2825, 3356, 0, 10, 1);//Rune Ore		
		spawnWithObject(7493, 2828, 3358, 0, 10, 1);//Adamant Ore
		spawnWithObject(7493, 2829, 3358, 0, 10, 1);//Adamant Ore
		spawnWithObject(7493, 2830, 3358, 0, 10, 1);//Adamant Ore
		spawnWithObject(7493, 2831, 3357, 0, 10, 1);//Adamant Ore
		spawnWithObject(7493, 2831, 3356, 0, 10, 1);//Adamant Ore		
		spawnWithObject(7491, 2830, 3354, 0, 10, 1);//Gold Ore
		spawnWithObject(7491, 2831, 3354, 0, 10, 1);//Gold Ore
		spawnWithObject(7491, 2832, 3354, 0, 10, 1);//Gold Ore
		spawnWithObject(7491, 2833, 3354, 0, 10, 1);//Gold Ore
		spawnWithObject(7489, 2833, 3356, 0, 10, 1);//Coal
		spawnWithObject(7489, 2834, 3356, 0, 10, 1);//Coal
		spawnWithObject(7489, 2835, 3356, 0, 10, 1);//Coal
		spawnWithObject(7489, 2835, 3354, 0, 10, 1);//Coal
		
		spawnWithObject(-1, 1439, 3817, 0, 10, 2);//copper
		spawnWithObject(-1, 1438, 3816, 0, 10, 2);//copper
		spawnWithObject(-1, 1439, 3815, 0, 10, 2);//copper
		spawnWithObject(-1, 1436, 3810, 0, 10, 2);//tin
		spawnWithObject(-1, 1438, 3810, 0, 10, 2);//tin
		spawnWithObject(-1, 1425, 3814, 0, 10, 2);//Iron
		spawnWithObject(-1, 1425, 3816, 0, 10, 2);//Iron
		spawnWithObject(-1, 1422, 3824, 0, 10, 2);//Silver
		spawnWithObject(-1, 1421, 3825, 0, 10, 2);//Silver
		spawnWithObject(-1, 1430, 3809, 0, 10, 2);//Coal
		spawnWithObject(-1, 1429, 3809, 0, 10, 2);//Coal
		spawnWithObject(-1, 1427, 3810, 0, 10, 2);//Coal
		spawnWithObject(-1, 1429, 3807, 0, 10, 2);//Coal
		spawnWithObject(-1, 1426, 3808, 0, 10, 2);//Coal
		spawnWithObject(-1, 1426, 3821, 0, 10, 2);//Gold	
		spawnWithObject(-1, 1427, 3820, 0, 10, 2);//Gold
		spawnWithObject(-1, 1426, 3819, 0, 10, 2);//Gold
		spawnWithObject(-1, 1432, 3829, 0, 10, 2);//Gem
		spawnWithObject(-1, 1431, 3830, 0, 10, 2);//Gem
		spawnWithObject(-1, 1432, 3828, 0, 10, 2);//Gem
		spawnWithObject(-1, 1422, 3833, 0, 10, 2);//mith
		spawnWithObject(-1, 1423, 3832, 0, 10, 2);//mith
		spawnWithObject(-1, 1421, 3831, 0, 10, 2);//mith
		spawnWithObject(-1, 1425, 3841, 0, 10, 2);//addy
		spawnWithObject(-1, 1426, 3845, 0, 10, 2);//addy
		spawnWithObject(-1, 1426, 3844, 0, 10, 2);//addy
		spawnWithObject(-1, 1426, 3843, 0, 10, 2);//addy
		spawnWithObject(-1, 1429, 3840, 0, 10, 2);//addy
		spawnWithObject(-1, 1432, 3843, 0, 10, 2);//rune
		spawnWithObject(-1, 1433, 3843, 0, 10, 2);//rune
		spawnWithObject(-1, 1433, 3842, 0, 10, 2);//rune
		spawnWithObject(-1, 1435, 3842, 0, 10, 2);//rune
		spawnWithObject(-1, 1434, 3840, 0, 10, 2);//rune
		spawnWithObject(-1, 3042, 9763, 0, 10, 2);//lovakite	
		spawnWithObject(-1, 3043, 9763, 0, 10, 2);//lovakite
		spawnWithObject(-1, 3044, 9763, 0, 10, 2);//lovakite
		spawnWithObject(-1, 3045, 9763, 0, 10, 2);//lovakite

		spawnWithObject(9030, 2524, 3864, 0, 10, 2);//Gem dzone

		/* Mining Zone */
		spawnWithObject(10517, 3045, 9781, 0, 10, 2);//Banks

		spawnWithObject(7484, 3046, 9777, 0, 10, 2);//copper	
		spawnWithObject(7484, 3045, 9777, 0, 10, 2);//copper
		spawnWithObject(7484, 3044, 9777, 0, 10, 2);//copper
		spawnWithObject(7484, 3043, 9776, 0, 10, 2);//copper
		spawnWithObject(7485, 3037, 9779, 0, 10, 2);//tin
		spawnWithObject(7485, 3036, 9777, 0, 10, 2);//tin
		spawnWithObject(7485, 3035, 9778, 0, 10, 2);//tin
		spawnWithObject(7485, 3035, 9777, 0, 10, 2);//tin
		spawnWithObject(7455, 3036, 9772, 0, 10, 2);//Iron
		spawnWithObject(7455, 3034, 9771, 0, 10, 2);//Iron
		spawnWithObject(7455, 3036, 9770, 0, 10, 2);//Iron
		spawnWithObject(7455, 3037, 9770, 0, 10, 2);//Iron
		spawnWithObject(7490, 3042, 9775, 0, 10, 2);//Silver	
		spawnWithObject(7490, 3042, 9774, 0, 10, 2);//Silver
		spawnWithObject(7490, 3042, 9773, 0, 10, 2);//Silver
		spawnWithObject(7489, 3035, 9765, 0, 10, 2);//Coal
		spawnWithObject(7489, 3035, 9764, 0, 10, 2);//Coal
		spawnWithObject(7489, 3035, 9763, 0, 10, 2);//Coal
		spawnWithObject(7489, 3036, 9761, 0, 10, 2);//Coal	
		spawnWithObject(7489, 3036, 9761, 0, 10, 2);//Coal
		spawnWithObject(7491, 3041, 9765, 0, 10, 2);//Gold
		spawnWithObject(7491, 3040, 9765, 0, 10, 2);//Gold
		spawnWithObject(7491, 3042, 9763, 0, 10, 2);//Gold
		spawnWithObject(7491, 3043, 9763, 0, 10, 2);//Gold
		spawnWithObject(9030, 3053, 9783, 0, 10, 2);//Gem
		spawnWithObject(9030, 3052, 9783, 0, 10, 2);//Gem
		spawnWithObject(9030, 3054, 9782, 0, 10, 2);//Gem
		spawnWithObject(7492, 3058, 9774, 0, 10, 2);//mith	
		spawnWithObject(7492, 3055, 9772, 0, 10, 2);//mith	
		spawnWithObject(7492, 3052, 9769, 0, 10, 2);//mith	
		spawnWithObject(7493, 3049, 9760, 0, 10, 2);//addy
		spawnWithObject(7493, 3050, 9760, 0, 10, 2);//addy
		spawnWithObject(7493, 3051, 9760, 0, 10, 2);//addy	
		spawnWithObject(7494, 3044, 9764, 0, 10, 2);//rune	

		/* Essence */
		spawnWithObject(7471, 2927, 4814, 0, 10, 2);//ess
		
		/* New Dzone */
		spawnWithObject(10517, 2512, 3868, 0, 10, 2);//Banks	
		spawnWithObject(10517, 2514, 3868, 0, 10, 2);//Banks
		spawnWithObject(10517, 2515, 3868, 0, 10, 2);//Banks
		spawnWithObject(10517, 2537, 3883, 0, 10, 2);//Banks
		spawnWithObject(10517, 2520, 3861, 0, 10, 0);//Banks

		spawnWithObject(2191, 2511, 3859, 0, 10, 1);//Crystal chest
		spawnWithObject(4309, 2518, 3858, 0, 10, 2);//Spinning wheel
		spawnWithObject(11601, 2515, 3858, 0, 10, 3);//Pottery
		spawnWithObject(26809, 2508, 3856, 0, 10, 4);//vote booth
		spawnWithObject(2097, 2547, 3894, 0, 10, 1);//Anvil
		spawnWithObject(16469, 2544, 3895, 0, 10, 1);//Furnace
		spawnWithObject(7491, 2525, 3892, 0, 10, 1);//Gold Ore
		spawnWithObject(7491, 2525, 3894, 0, 10, 1);//Gold Ore
		spawnWithObject(7493, 2526, 3892, 0, 10, 1);//Adamant Ore
		spawnWithObject(7494, 2532, 3891, 0, 10, 1);//Rune Ore
		spawnWithObject(7494, 2533, 3890, 0, 10, 1);//Rune Ore
		spawnWithObject(11764, 2533, 3878, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2531, 3875, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2536, 3871, 0, 10, 1);//Magic Tree
		spawnWithObject(11758, 2546, 3877, 0, 10, 1);//Yew Tree
		spawnWithObject(11758, 2542, 3879, 0, 10, 1);//Yew Tree

		
		/* Skilling Zone */
		spawnWithObject(1276, 1833, 3496, 0, 10, 0);//Tree
		spawnWithObject(1278, 1830, 3496, 0, 10, 0);//Tree
		spawnWithObject(11756, 1826, 3496, 0, 10, 0);//Oak Tree
		spawnWithObject(11755, 1823, 3496, 0, 10, 0);//Willow Tree
		spawnWithObject(11762, 1820, 3496, 0, 10, 0);//Maple Tree
		spawnWithObject(11758, 1816, 3496, 0, 10, 0);//Yew Tree
		spawnWithObject(11764, 1812, 3496, 0, 10, 0);//Magic Tree
		
		/* Wilderness Resource trivia */
		spawnWithObject(7494, 3195, 3942, 0, 10, 3);
		spawnWithObject(7494, 3194, 3943, 0, 10, 3);
		spawnWithObject(7494, 3175, 3937, 0, 10, 3);
		spawnWithObject(7494, 3175, 3943, 0, 10, 3);
		
		/* Blood crafting */
		spawnWithObject(4090, 2792, 3322, 0, 10, 0);//Altar
	
		/* Crafting */
		spawnWithObject(4309, 2751, 3446, 0, 10, 3);//Spinning wheel
		spawnWithObject(11601, 2751, 3449, 0, 10, 2);//Potter

		/* Barrows */
		spawnWithObject(20723, 3551, 9695, 0, 10, 4);
		
		/** Rune ores at mining */
		spawnWithObject(7494, 3051, 9765, 0, 10, 3);
		spawnWithObject(7494, 3052, 9766, 0, 10, 3);
		
		
		/** Weapon Game **/
		deleteWithObject(1863, 5328, 0);
		deleteWithObject(1863, 5326, 0);
		deleteWithObject(1863, 5323, 0);
		deleteWithObject(1862, 5327, 0);
		deleteWithObject(1862, 5326, 0);
		deleteWithObject(1862, 5325, 0);
		deleteWithObject(1865, 5325, 0);
		deleteWithObject(1863, 5321, 0);
		deleteWithObject(1865, 5321, 0);
		deleteWithObject(1865, 5323, 0);
		deleteWithObject(1863, 5319, 0);
		deleteWithObject(1862, 5319, 0);
		deleteWithObject(1863, 5317, 0);
		deleteWithObject(1865, 5319, 0);
		deleteWithObject(1862, 5321, 0);
		deleteWithObject(1862, 5323, 0);
		spawnWithObject(1, 1866, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(1, 1865, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(11005, 1864, 5323, 0, 10, 1);//Barrier	
		spawnWithObject(11005, 1863, 5323, 0, 10, 1);//Barrier	
		spawnWithObject(1, 1862, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(1, 1861, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(10517, 1861, 5330, 0, 10, 0);//Barrier	
		spawnWithObject(10517, 1862, 5330, 0, 10, 0);//Barrier	
		spawnWithObject(10517, 1863, 5330, 0, 10, 0);//Barrier	
		spawnWithObject(11744, 1864, 5330, 0, 10, 0);//Barrier
		spawnWithObject(11744, 1865, 5330, 0, 10, 0);//Barrier
		spawnWithObject(11744, 1866, 5330, 0, 10, 0);//Barrier
		
		/** Duel trivia */
		spawnWithObject(409, 3366, 3271, 0, 10, 10);//Altar	
		spawnWithObject(6552, 3370, 3271, 0, 10, 10);//Ancient Altar
		
		/* Crafting Area */
		spawnWithObject(11744, 2748, 3451, 0, 10, 0);// Banks
		
		/** Farming Areas */
		spawnWithObject(11744, 2804, 3463, 0, 10, 1);// Catherby Banks
		spawnWithObject(11744, 3599, 3522, 0, 10, 0);// Banks
		spawnWithObject(11744, 3056, 3311, 0, 10, 0);// Banks
		spawnWithObject(11744, 2662, 3375, 0, 10, 0);// Banks

		/** Mining banks */
		spawnWithObject(11744, 3047, 9765, 0, 10, 0);
		spawnWithObject(11744, 3045, 9765, 0, 10, 0);
		spawnWithObject(11744, 3044, 9776, 0, 10, 0);
		spawnWithObject(11744, 3045, 9776, 0, 10, 0);
		spawnWithObject(11744, 3046, 9776, 0, 10, 0);
		spawnWithObject(11744, 2930, 4821, 0, 10, 0);// Essences
		
		spawnWithObject(10439, 3426, 2930, 0, 10, 0);//elid
		spawnWithObject(28861, 3429, 2931, 0, 10, 0);//bank chest

		/* Skilling Area */
		spawnWithObject(7488, 1819, 3510, 0, 10, 0);
		spawnWithObject(7488, 1820, 3509, 0, 10, 0);
		spawnWithObject(7488, 1821, 3508, 0, 10, 0);
		spawnWithObject(13719, 1822, 3508, 0, 10, 0);
		spawnWithObject(13719, 1823, 3508, 0, 10, 0);
		spawnWithObject(13719, 1824, 3508, 0, 10, 0);
		spawnWithObject(14168, 1825, 3508, 0, 10, 0);
		spawnWithObject(14168, 1826, 3508, 0, 10, 0);
		spawnWithObject(14168, 1827, 3508, 0, 10, 0);
		spawnWithObject(7494, 1828, 3508, 0, 10, 0);
		spawnWithObject(7494, 1829, 3508, 0, 10, 0);
		spawnWithObject(7494, 1830, 3508, 0, 10, 0);
		spawnWithObject(7489, 1831, 3508, 0, 10, 0);
		spawnWithObject(7489, 1832, 3509, 0, 10, 0);
		spawnWithObject(2030, 1805, 3494, 0, 10, 3);
		spawnWithObject(2097, 1808, 3498, 0, 10, 0);
		spawnWithObject(12269, 1810, 3509, 0, 10, 0);
		spawnWithObject(26275, 1836, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1837, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1838, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1839, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1840, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1841, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1842, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1843, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1844, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1845, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1846, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1847, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1848, 3511, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3493, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3492, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3491, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3490, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3489, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3488, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3487, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3486, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3485, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3484, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3483, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3482, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3481, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3480, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3479, 0, 10, 1);//Zeah Lamp
		spawnWithObject(26275, 1835, 3478, 0, 10, 1);//Zeah Lamp
		spawnWithObject(11744, 1810, 3503, 0, 10, 1);
		spawnWithObject(11744, 1801, 3501, 0, 10, 1);
		spawnWithObject(11744, 1808, 3513, 0, 10, 0);
		/** Deleting Objects */
		delete(3079, 3501, 0);//Home gate
		delete(3080, 3501, 0);//Home gate
		delete(3445, 3554, 2);//Slayer tower door
		
		/** Remove objects */
		deleteWithObject(3085, 3506, 0);
		deleteWithObject(3088, 3511, 0);
		deleteWithObject(3088, 3510, 0);
		deleteWithObject(3088, 3509, 0);
		deleteWithObject(3089, 3509, 0);
		deleteWithObject(3089, 3510, 0);
		deleteWithObject(3089, 3511, 0);
		deleteWithObject(3090, 3503, 0);
		deleteWithObject(3098, 3499, 0);
		deleteWithObject(3095, 3498, 0);
		deleteWithObject(3095, 3499, 0);
		deleteWithObject(3092, 3496, 0);
		deleteWithObject(3091, 3495, 0);
		deleteWithObject(3090, 3494, 0);
		deleteWithObject(3090, 3496, 0);
		deleteWithObject(1812, 3510, 0);
		deleteWithObject(1812, 3509, 0);
		
		spawnWithObject(412, 3085, 3509, 0, 10, 1);// chaos alter
		spawnWithObject(11744, 3083, 3514, 0, 10, 0);// Banks
		spawnWithObject(11744, 3084, 3514, 0, 10, 0);// Banks
		spawnWithObject(10517, 3426, 2892, 0, 10, 1);// Banks
		spawnWithObject(10517, 3426, 2890, 0, 10, 1);// Banks	
		spawnWithObject(10517, 2809, 3442, 0, 10, 0);// Banks
		spawnWithObject(10517, 2811, 3442, 0, 10, 0);// Banks	
		spawnWithObject(10517, 2807, 3442, 0, 10, 0);// Banks	
		
			
		/** Webs */
		delete(3105, 3958, 0);
		delete(3106, 3958, 0);
		delete(3093, 3957, 0);
		delete(3095, 3957, 0);
		delete(3092, 3957, 0);
		delete(3158, 3951, 0);
		deleteWithObject(2543, 4715, 0);
		spawnWithObject(734, 3105, 3958, 0, 10, 3);
		spawnWithObject(734, 3106, 3958, 0, 10, 3);
		spawnWithObject(734, 3158, 3951, 0, 10, 1);
		spawnWithObject(734, 3093, 3957, 0, 10, 0);
		spawnWithObject(734, 3095, 3957, 0, 10, 0);	
		delete(2543, 4715, 0);	
		delete(2855, 3546, 0);
		delete(2854, 3546, 0);

		/** Clipping */
		setClipToZero(3445, 3554, 2);
		setClipToZero(3119, 9850, 0);
		setClipToZero(3002, 3961, 0);
		setClipToZero(3002, 3960, 0);
		setClipToZero(2539, 4716, 0);
		setClipToZero(3068, 10255, 0);
		setClipToZero(3068, 10256, 0);
		setClipToZero(3068, 10258, 0);
		setClipToZero(3067, 10255, 0);
		setClipToZero(3066, 10256, 0);
		setClipToZero(3426, 3555, 1);
		setClipToZero(3427, 3555, 1);
		setClipToZero(3005, 3953, 0);
		setClipToZero(3005, 3952, 0);
		setClipToZero(2551, 3554, 0);
		setClipToZero(2551, 3555, 0);
		setClipToZero(2833, 3352, 0);
		setClipToZero(2996, 3960, 0);
		setClipToZero(3104, 3497, 0);
		setClipToZero(3105, 3497, 0);
		setClipToZero(3104, 3498, 0);
		setClipToZero(3105, 3498, 0);
		setClipToZero(3104, 3499, 0);
		setClipToZero(3105, 3499, 0);
		setClipToZero(3104, 3500, 0);
		setClipToZero(3105, 3500, 0);

		for (GameObject i : active) {
			send(i);
		}

		logger.info("All object spawns have been loaded successfully.");
	}
	
	private static Logger logger = Logger.getLogger(MapLoading.class.getSimpleName());

	private static final void delete(int x, int y, int z) {
		RSObject object = Region.getObject(x, y, z);

		if (Region.getDoor(x, y, z) != null) {
			Region.removeDoor(x, y, z);
		}

		if (object == null) {
			if (z > 0)
				active.add(new GameObject(2376, x, y, z, 10, 0));
			return;
		}

		MapLoading.removeObject(object.getId(), x, y, z, object.getType(), object.getFace());

		if ((object.getType() != 10) || (z > 0))
			active.add(new GameObject(2376, x, y, z, object.getType(), 0));
	}

	private static final void deleteWithObject(int x, int y, int z) {
		RSObject object = Region.getObject(x, y, z);

		if (Region.getDoor(x, y, z) != null) {
			Region.removeDoor(x, y, z);
		}

		if (object == null) {
			active.add(new GameObject(2376, x, y, z, 10, 0));
			return;
		}

		MapLoading.removeObject(object.getId(), x, y, z, object.getType(), object.getFace());

		active.add(new GameObject(2376, x, y, z, object.getType(), 0));
	}
	
	private static final void remove(int x, int y, int z) {
	RSObject object = Region.getObject(x, y, z);
	
	if (Region.getDoor(x, y, z) != null) {
		Region.removeDoor(x, y, z);
	}
	
	if (object == null) {
		active.add(new GameObject(2376, x, y, z, 10, 0));
		return;
	}
	
	MapLoading.removeObject(object.getId(), x, y, z, object.getType(), object.getFace());
	
	active.add(new GameObject(2376, x, y, z, object.getType(), 0));
	Region region = Region.getRegion(x, y);

	region.setClipToZero(x, y, z);
}
	

	private static final void deleteWithObject(int x, int y, int z, int type) {
		active.add(new GameObject(2376, x, y, z, type, 0));
	}

	public static List<GameObject> getActive() {
		return active;
	}

	public static final GameObject getBlankObject(Location p) {
		return new GameObject(2376, p.getX(), p.getY(), p.getZ(), 10, 0, false);
	}

	public static GameObject getBlankObject(Location p, int type) {
		return new GameObject(2376, p.getX(), p.getY(), p.getZ(), type, 0, false);
	}

	public static GameObject getGameObject(int x, int y, int z) {
		int index = active.indexOf(new GameObject(x, y, z));

		if (index == -1) {
			return null;
		}

		return active.get(index);
	}

	public static Queue<GameObject> getSend() {
		return send;
	}

	public static boolean objectExists(Location location) {
		for (GameObject object : active) {
			if (location.equals(object.getLocation())) {
				return true;
			}
		}
		return false;
	}

	public static void process() {
		for (Iterator<GameObject> i = register.iterator(); i.hasNext();) {
			GameObject reg = i.next();
			active.remove(reg);
			active.add(reg);
			send.add(reg);

			i.remove();
		}
	}

	public static void queueSend(GameObject o) {
		send.add(o);
	}

	public static void register(GameObject o) {
		register.add(o);
	}

	public static void remove(GameObject o) {
		removeFromList(o);
		send.add(getBlankObject(o.getLocation(), o.getType()));
	}

	public static void remove2(GameObject o) {
		send.add(getBlankObject(o.getLocation(), o.getType()));
	}

	public static void removeFromList(GameObject o) {
		active.remove(o);
	}

	private static final void removeWithoutClip(int x, int y, int z, int type) {
	}

	public static void send(GameObject o) {
		for (Player player : World.getPlayers())
			if ((player != null) && (player.isActive())) {
				if ((player.withinRegion(o.getLocation())) && (player.getLocation().getZ() % 4 == o.getLocation().getZ() % 4))
					player.getObjects().add(o);
			}
	}

	public static void setClipToZero(int x, int y, int z) {
		Region region = Region.getRegion(x, y);

		region.setClipToZero(x, y, z);
	}

	public static void setClipped(int x, int y, int z) {
		Region region = Region.getRegion(x, y);

		region.setClipping(x, y, z, 0x12801ff);
	}

	public static void setProjecileClipToInfinity(int x, int y, int z) {
		Region region = Region.getRegion(x, y);

		region.setProjecileClipToInfinity(x, y, z);
	}

	private static final void spawn(int id, int x, int y, int z, int type, int face) {
		MapLoading.addObject(false, id, x, y, z, type, face);
	}
	
	public static final void spawnWithObject(int id, Location location, int type, int face) {
		active.add(new GameObject(id, location.getX(), location.getY(), location.getZ(), type, face));
		MapLoading.addObject(false, id, location.getX(), location.getY(), location.getZ(), type, face);
	
		send(new GameObject(id, location.getX(), location.getY(), location.getZ(), type, face));
	}

	public static final void spawnWithObject(int id, int x, int y, int z, int type, int face) {
		active.add(new GameObject(id, x, y, z, type, face));
		MapLoading.addObject(false, id, x, y, z, type, face);

		send(new GameObject(id, x, y, z, type, face));
	}
}
