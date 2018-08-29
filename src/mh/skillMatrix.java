package mh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class skillMatrix {
    private List<String> mDefaultSkills = new ArrayList<>();
    private HashMap<Integer, String> mDefaultFindSkills = new HashMap<>();
    private int[] mDefaultSkillMatrix;

    skillMatrix()   {
        BufferedReader defaultSkills = null;
        try {
            defaultSkills = new BufferedReader(new FileReader("skillList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (defaultSkills != null) {
            defaultSkills.lines().forEachOrdered(s -> mDefaultSkills.add(s));
        }
        try {
            assert defaultSkills != null;
            defaultSkills.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(mDefaultSkills);
        buildHash(mDefaultSkills, mDefaultFindSkills);
        mDefaultSkillMatrix = new int[mDefaultFindSkills.size()];

    }

    HashMap<Integer, String> getDefaultMap(){
        return mDefaultFindSkills;
    }

    int[] getDefaultMatrix(){
        return mDefaultSkillMatrix;
    }

    List<String> getDefaultSkills(){ return mDefaultSkills; }

    private void buildHash(List<String> listSkills, HashMap<Integer, String> mMap){
        for(String skills : listSkills) {
            mMap.put(listSkills.indexOf(skills), skills);
        }
    }

    static Integer getKeyByValue(HashMap<Integer, String> map, Object value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    void buildMatrix(List<String> skills, int[] levels) {
        for (String localSkill : skills)
            mDefaultSkillMatrix[getKeyByValue(mDefaultFindSkills, localSkill)] = levels[skills.indexOf(localSkill)];

    }
}

