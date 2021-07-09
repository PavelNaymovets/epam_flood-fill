package com.epam.rd.autocode.floodfill;

public interface FloodFill {

    char LAND = '█';
    char WATER = '░';

    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
        return new FloodFill() {
            @Override
            public void flood(String map, FloodLogger logger) {
                logger.log(map);
                String[] initString = map.split("\n");
                char [][] str = new char [initString.length][initString[0].length()];

                int i = 0;
                for(String word : initString) {
                    char[] chars = word.toCharArray();
                    for (int j = 0; j < str[i].length; j++) {
                        str[i][j] = chars[j];
                    }
                    i++;
                }

                char [][] strNew = new char [initString.length][initString[0].length()];
                int ii = 0;
                for(String word : initString) {
                    char[] chars = word.toCharArray();
                    for (int j = 0; j < strNew[ii].length; j++) {
                        strNew[ii][j] = chars[j];
                    }
                    ii++;
                }

                for(int k = 0; k < str.length; k++){
                    for(int p = 0; p < str[k].length; p++){
                        if(str[k][p] == WATER){
                            if(k == 0 && p == 0){
                                strNew[k][p] = WATER;
                                strNew[k][p+1] = WATER;
                                strNew[k+1][p] = WATER;
                            }else if(k == 0 && p == str[k].length-1){
                                strNew[k][p] = WATER;
                                strNew[k][p-1] = WATER;
                                strNew[k+1][p] = WATER;
                            } else if(k == str.length-1 && p == 0){
                                strNew[k][p] = WATER;
                                strNew[k - 1][p] = WATER;
                                strNew[k][p + 1] = WATER;
                            } else if(k == str.length-1 && p == str[k].length-1){
                                strNew[k][p] = WATER;
                                strNew[k - 1][p] = WATER;
                                strNew[k][p - 1] = WATER;
                            } else if(k > 0 && k < str.length - 1 && p == 0){
                                strNew[k][p] = WATER;
                                strNew[k - 1][p] = WATER;
                                strNew[k][p + 1] = WATER;
                                strNew[k + 1][p] = WATER;
                            } else if(k > 0 && k < str.length - 1 && p == str[k].length-1){
                                strNew[k][p] = WATER;
                                strNew[k - 1][p] = WATER;
                                strNew[k][p - 1] = WATER;
                                strNew[k + 1][p] = WATER;
                            } else if(k == 0 && p > 0 && p < str[k].length-1){
                                strNew[k][p] = WATER;
                                strNew[k][p - 1] = WATER;
                                strNew[k][p + 1] = WATER;
                                strNew[k + 1][p] = WATER;
                            } else if(k == str.length - 1 && p > 0 && p < str[k].length-1){
                                strNew[k][p] = WATER;
                                strNew[k][p - 1] = WATER;
                                strNew[k][p + 1] = WATER;
                                strNew[k - 1][p] = WATER;
                            } else if(k > 0 && k < str.length - 1 && p > 0 && p < str[k].length-1){
                                strNew[k][p] = WATER;
                                strNew[k - 1][p] = WATER;
                                strNew[k][p + 1] = WATER;
                                strNew[k + 1][p] = WATER;
                                strNew[k][p - 1] = WATER;
                            }
                        }
                    }
                }

                String floodState = "";

                for (int m = 0; m < strNew.length; m++){
                    for(int n =0 ; n < strNew[m].length; n++){
                        floodState += strNew[m][n];
                    }
                      if(m < strNew.length - 1){
                          floodState += "\n";
                      }
                }

                FloodLogger floodLogger = new FloodLogger() {
                    @Override
                    public void log(String floodState) {
                        System.out.println(floodState + "\n");
                    }
                };

                if(floodState.contains(String.valueOf(LAND))){
                    flood(floodState, floodLogger);
                }
                else{
                    System.out.println(floodState);
                }
            };
        };
    }
}
