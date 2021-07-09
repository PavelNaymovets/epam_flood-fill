package com.epam.rd.autocode.floodfill;

public interface FloodFill {

    char LAND = '█';
    char WATER = '░';

    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
        FloodFill floodFill = new FloodFill() {
            @Override
            public void flood(String map, FloodLogger logger) {
                logger.log(map);
                String[] initString = map.split("\n");
                String[][] str = new String[initString.length][initString[0].length()];

                int i = 0;
                for(String word : initString) {
                    char[] chars = word.toCharArray();
                    for (int j = 0; j < str[i].length; j++) {
                        str[i][j] = String.valueOf(chars[j]);
                    }
                    i++;
                }

                String[][] strNew = new String[initString.length][initString[0].length()];
                int ii = 0;
                for(String word : initString) {
                    char[] chars = word.toCharArray();
                    for (int j = 0; j < strNew[ii].length; j++) {
                        strNew[ii][j] = String.valueOf(chars[j]);
                    }
                    ii++;
                }

                for(int k = 0; k < str.length; k++){
                    for(int p = 0; p < str[k].length; p++){
                        if(str[k][p].equals("░")){
                            if(k == 0 && p == 0){
                                strNew[k][p] = "░";
                                strNew[k][p+1] = "░";
                                strNew[k+1][p] = "░";
                            }else if(k == 0 && p == str[k].length-1){
                                strNew[k][p] = "░";
                                strNew[k][p-1] = "░";
                                strNew[k+1][p] = "░";
                            } else if(k == str.length-1 && p == 0){
                                strNew[k][p] = "░";
                                strNew[k - 1][p] = "░";
                                strNew[k][p + 1] = "░";
                            } else if(k == str.length-1 && p == str[k].length-1){
                                strNew[k][p] = "░";
                                strNew[k - 1][p] = "░";
                                strNew[k][p - 1] = "░";
                            } else if(k > 0 && k < str.length - 1 && p == 0){
                                strNew[k][p] = "░";
                                strNew[k - 1][p] = "░";
                                strNew[k][p + 1] = "░";
                                strNew[k + 1][p] = "░";
                            } else if(k > 0 && k < str.length - 1 && p == str[k].length-1){
                                strNew[k][p] = "░";
                                strNew[k - 1][p] = "░";
                                strNew[k][p - 1] = "░";
                                strNew[k + 1][p] = "░";
                            } else if(k == 0 && p > 0 && p < str[k].length-1){
                                strNew[k][p] = "░";
                                strNew[k][p - 1] = "░";
                                strNew[k][p + 1] = "░";
                                strNew[k + 1][p] = "░";
                            } else if(k == str.length - 1 && p > 0 && p < str[k].length-1){
                                strNew[k][p] = "░";
                                strNew[k][p - 1] = "░";
                                strNew[k][p + 1] = "░";
                                strNew[k - 1][p] = "░";
                            } else if(k > 0 && k < str.length - 1 && p > 0 && p < str[k].length-1){
                                strNew[k][p] = "░";
                                strNew[k - 1][p] = "░";
                                strNew[k][p + 1] = "░";
                                strNew[k + 1][p] = "░";
                                strNew[k][p - 1] = "░";
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

                if(floodState.contains("█")){
                    flood(floodState, floodLogger);
                } else{
                    System.out.println(floodState);
                }
            };
        };
        return floodFill;
    }
}
