package homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationHashMapTest {

    @Test
    public void testHasCodesAreConstant(){
        int code1 = StationHashMap.hashFunction("Frederik", 1000000);
        System.out.println(code1);
        int code2 = StationHashMap.hashFunction("Fred"+"Drik", 1000000);
        System.out.println(code2);
        assertFalse(code1 == code2);
    }

    @Test
    public void testHashCodesForDifferentAreDifferent(){
        int code1 = StationHashMap.hashFunction("Frederik", 1000000);
        int code2 = StationHashMap.hashFunction("Menrina", 1000000);
        assertTrue(code1 != code2);
    }

//    @Test
//    public void testEmptyHashMapIsEmpty(){
//        StationHashMap map = new StationHashMap();
//        assertTrue(map.isEmpty());
//        assertFalse(map.containsKey("Fronk"));
//        assertEquals(0, map.getCount());
//        assertNull(map.remove("Fronk"));
//
//    }

}
