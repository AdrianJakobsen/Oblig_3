package Oppgave2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MyHashMapTest {


    MyHashMap hashMap;

    @Test
    public void put_addFourElements_ExcpectedOutput(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "sword");
        hashMap.put("Random", "shit");
        hashMap.put("key", "value");
        hashMap.put("pai", "spear");
        assertEquals("[[Random, [shit]][pai, [sword, spear]][key, [value]]]", hashMap.toString());
    }

    @Test
    public void put_returnsAddedValue_balrog(){
        hashMap = new MyHashMap();
        assertEquals("balrog", hashMap.put("Tower", "balrog"));
    }

    @Test
    public void put_returnsAddedValueSameKeyDiffrentValue_charizard(){
        hashMap = new MyHashMap();
        hashMap.put("Tower", "balrog");
        assertEquals("charizard", hashMap.put("Tower", "charizard"));
    }

    @Test
    public void put_addFourDiffrentEntrys_sizefour(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "sword");
        hashMap.put("Random", "shit");
        hashMap.put("key", "value");
        hashMap.put("soldier", "spear");
        assertEquals(4, hashMap.size());
    }

    @Test
    public void put_addSixEntrysSomeHaveSameKey_sizeThree(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "sword");
        hashMap.put("Random", "shit");
        hashMap.put("key", "value");
        hashMap.put("pai", "spear");
        hashMap.put("Random", "values");
        hashMap.put("key", "blade");
        assertEquals(3, hashMap.size());
    }

    @Test
    public void getAll_getAllValuesOfPai_swordPcLight(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "sword");
        hashMap.put("Random", "shit");
        hashMap.put("key", "value");
        hashMap.put("pai", "Pc");
        hashMap.put("pai", "light");
        assertEquals("[sword, Pc, light]", hashMap.getAll("pai").toString());
    }

    @Test
    public void getAll_getAllValuesOfKey_valueBladeBomb(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "sword");
        hashMap.put("Random", "shit");
        hashMap.put("key", "value");
        hashMap.put("key", "blade");
        hashMap.put("key", "bomb");
        assertEquals("[bomb, value, blade]", hashMap.getAll("key").toString());
    }

    @Test
    public void getAll_getAllValuesOfCake_boxingLueBot(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "boxing");
        hashMap.put("Random", "shit");
        hashMap.put("pai", "value");
        hashMap.put("pai", "lue");
        hashMap.put("pai", "bot");
        assertEquals("[bot, boxing, value, lue]", hashMap.getAll("pai").toString());
    }

    @Test
    public void getAll_getNonexsistantKey_exception(){
        hashMap = new MyHashMap();
        hashMap.put("pai", "sword");
        hashMap.put("Random", "shit");
        hashMap.put("key", "value");
        assertEquals(null, hashMap.getAll("lazy key"));
    }

    @Test
    public void getAll_AddDiffrentValueObjects_APai123(){
        hashMap = new MyHashMap();
        hashMap.put("key", 123);
        hashMap.put("key", "pai");
        hashMap.put("key", 'A');
        assertEquals("[A, pai, 123]", hashMap.getAll("key").toString());
    }
}