package mh;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class skillMatrixTest {

    @Test
    public void buildMatrix() {
        skillMatrix mh = new skillMatrix();
        List<String> mSkills = new ArrayList<>();
        mSkills.add("Fire Resistance");
        mSkills.add("Evade Extender");
        int[] mSkillMatrix = new int[2];
        mSkillMatrix[0] = 1;
        mSkillMatrix[1] = 5;
        mh.buildMatrix(mSkills, mSkillMatrix);
        int x = skillMatrix.getKeyByValue(mh.getDefaultMap(), mSkills.get(0));
        int y = skillMatrix.getKeyByValue(mh.getDefaultMap(), mSkills.get(1));
        assertEquals(mh.getDefaultMatrix()[x], 1);
        assertEquals(mh.getDefaultMatrix()[y], 5);

    }
}













//    @Test
//    public void setSkills() {
//        skillMatrix mh = new skillMatrix();
//        List<String> mSkills = new ArrayList<>();
//        mSkills.add("Attack");
//        mSkills.add("Violet");
//        mh.setSkills(mSkills);
//        assertEquals(mh.getSkills(), mSkills);
//    }

//    @Test
//    public void getDefaultMap() {
//        skillMatrix mh = new skillMatrix();
//        HashMap mHash = mh.getDefaultMap();
//        Iterator<Map.Entry<Integer, String>> it = mHash.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry pair = (Map.Entry)((Iterator) it).next();
//            //System.out.println(pair.getKey() + " = " + pair.getValue());
//        }
//        assertEquals(mh.getDefaultMatrix().length, mHash.size());
//
//    }
//
//    @Test
//    public void buildHash() {
//        skillMatrix mh = new skillMatrix();
//        List<String> mSkills = new ArrayList<>();
//        mSkills.add("Attack");
//        mSkills.add("Violet");
//        mh.setSkills(mSkills);
//        mh.buildHash();
//        HashMap<Integer, String> mHash = mh.getMap();
//
//        Iterator<Map.Entry<Integer, String>> it = mHash.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry pair = (Map.Entry)((Iterator) it).next();
//            //System.out.println(pair.getKey() + " = " + pair.getValue());
//            assertEquals(pair.getValue(), mSkills.get((Integer) pair.getKey()));
//        }
//        assertNotEquals(mHash.get(0), mSkills.get(1));
//        assertEquals(mHash.get(0), mSkills.get(0));
//
//
//    }
//
//    @Test
//    public void getKeyByValue() {
//        skillMatrix mh = new skillMatrix();
//        List<String> mSkills = new ArrayList<>();
//        mSkills.add("Attack");
//        mSkills.add("Violet");
//        mh.setSkills(mSkills);
//        mh.buildHash();
//        HashMap<Integer, String> mHash = mh.getMap();
//        Iterator<Map.Entry<Integer, String>> it = mHash.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry pair = (Map.Entry)((Iterator) it).next();
//            //System.out.println(pair.getKey() + " = " + pair.getValue());
//            assertEquals(skillMatrix.getKeyByValue(mh.getMap(), pair.getValue()), pair.getKey());
//        }
//        assertNotEquals((int) skillMatrix.getKeyByValue(mh.getMap(), "Attack"), 1);
//        assertEquals((int) skillMatrix.getKeyByValue(mh.getMap(), "Attack"), 0);
//
//    }
