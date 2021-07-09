package com.epam.rd.autocode.floodfill;

import java.util.Arrays;
public interface FloodFill {

    char LAND = '█';
    char WATER = '░';

    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
                return new FloodFill() {
                    @Override
                    public void flood(String map, FloodLogger logger) {
                        logger.log(map);
                        String[] array = map.split("\n");
                        char[][] charArray = new char[array.length][];
                        for (int i = 0; i < array.length; i++) {
                            charArray[i] = array[i].toCharArray();
                        }
                        char[][] charArrayCopy = copyArray(charArray);

                        for (int i = 0; i < charArray.length; i++) {
                            for (int j = 0; j < charArray[i].length; j++) {
                                if (charArray[i][j] == WATER) {
                                    if (i == 0) {
                                        if (j == 0) {
                                            charArrayCopy[i + 1][j] = WATER;
                                            charArrayCopy[i][j + 1] = WATER;
                                        } else if (j == charArray[i].length - 1) {
                                            charArrayCopy[i + 1][j] = WATER;
                                            charArrayCopy[i][j - 1] = WATER;
                                        } else {
                                            charArrayCopy[i + 1][j] = WATER;
                                            charArrayCopy[i][j + 1] = WATER;
                                            charArrayCopy[i][j - 1] = WATER;
                                        }

                                    } else if (i == charArray.length - 1) {
                                        if (j == 0) {
                                            charArrayCopy[i - 1][j] = WATER;
                                            charArrayCopy[i][j + 1] = WATER;
                                        } else if (j == charArray[i].length - 1) {
                                            charArrayCopy[i - 1][j] = WATER;
                                            charArrayCopy[i][j - 1] = WATER;
                                        } else {
                                            charArrayCopy[i - 1][j] = WATER;
                                            charArrayCopy[i][j + 1] = WATER;
                                            charArrayCopy[i][j - 1] = WATER;
                                        }
                                    } else if (j == 0) {
                                        charArrayCopy[i + 1][j] = WATER;
                                        charArrayCopy[i - 1][j] = WATER;
                                        charArrayCopy[i][j + 1] = WATER;
                                    } else if (j == charArray[i].length - 1) {
                                        charArrayCopy[i + 1][j] = WATER;
                                        charArrayCopy[i - 1][j] = WATER;
                                        charArrayCopy[i][j - 1] = WATER;
                                    } else if (i != charArray.length - 1 && j != charArray[i].length - 1) {
                                        charArrayCopy[i + 1][j] = WATER;
                                        charArrayCopy[i - 1][j] = WATER;
                                        charArrayCopy[i][j + 1] = WATER;
                                        charArrayCopy[i][j - 1] = WATER;
                                    }
                                }
                            }
                        }

                        if (isHasLand(charArrayCopy)){
                            flood(arrayToString(charArrayCopy), logger);
                        }else{
                            logger.log(arrayToString(charArrayCopy));
                        }

                    }
                };

            }

            default boolean isHasLand(char[][] array) {
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        if(array[i][j] == LAND){
                            return true;
                        }
                    }
                }
                return false;
            }

            default String arrayToString(char[][] array) {
                StringBuilder sb = new StringBuilder();
                for (char[] chars : array) {
                    for (int j = 0; j < chars.length; j++) {
                        if (j == chars.length - 1) {
                            sb.append(chars[j]);
                            sb.append("\n");
                        } else {
                            sb.append(chars[j]);
                        }
                    }
                }
                return sb.toString().trim();
            }

            default char[][] copyArray(char[][] array) {
                char[][] copy = new char[array.length][];
                for (int i = 0; i < array.length; i++) {
                    copy[i] = Arrays.copyOf(array[i], array[i].length);
                }
                return copy;
    }
}
//        return new FloodFill() {
//            @Override
//            public void flood(String map, FloodLogger logger) {
//                logger.log(map);
//                String[] initString = map.split("\n");
//                char [][] str = new char [initString.length][initString[0].length()];
//
//                int i = 0;
//                for(String word : initString) {
//                    char[] chars = word.toCharArray();
//                    for (int j = 0; j < str[i].length; j++) {
//                        str[i][j] = chars[j];
//                    }
//                    i++;
//                }
//
//                char [][] strNew = new char [initString.length][initString[0].length()];
//                int ii = 0;
//                for(String word : initString) {
//                    char[] chars = word.toCharArray();
//                    for (int j = 0; j < strNew[ii].length; j++) {
//                        strNew[ii][j] = chars[j];
//                    }
//                    ii++;
//                }
//
//                for(int k = 0; k < str.length; k++){
//                    for(int p = 0; p < str[k].length; p++){
//                        if(str[k][p] == WATER){
//                            if(k == 0 && p == 0){
//                                strNew[k][p] = WATER;
//                                strNew[k][p+1] = WATER;
//                                strNew[k+1][p] = WATER;
//                            }else if(k == 0 && p == str[k].length-1){
//                                strNew[k][p] = WATER;
//                                strNew[k][p-1] = WATER;
//                                strNew[k+1][p] = WATER;
//                            } else if(k == str.length-1 && p == 0){
//                                strNew[k][p] = WATER;
//                                strNew[k - 1][p] = WATER;
//                                strNew[k][p + 1] = WATER;
//                            } else if(k == str.length-1 && p == str[k].length-1){
//                                strNew[k][p] = WATER;
//                                strNew[k - 1][p] = WATER;
//                                strNew[k][p - 1] = WATER;
//                            } else if(k > 0 && k < str.length - 1 && p == 0){
//                                strNew[k][p] = WATER;
//                                strNew[k - 1][p] = WATER;
//                                strNew[k][p + 1] = WATER;
//                                strNew[k + 1][p] = WATER;
//                            } else if(k > 0 && k < str.length - 1 && p == str[k].length-1){
//                                strNew[k][p] = WATER;
//                                strNew[k - 1][p] = WATER;
//                                strNew[k][p - 1] = WATER;
//                                strNew[k + 1][p] = WATER;
//                            } else if(k == 0 && p > 0 && p < str[k].length-1){
//                                strNew[k][p] = WATER;
//                                strNew[k][p - 1] = WATER;
//                                strNew[k][p + 1] = WATER;
//                                strNew[k + 1][p] = WATER;
//                            } else if(k == str.length - 1 && p > 0 && p < str[k].length-1){
//                                strNew[k][p] = WATER;
//                                strNew[k][p - 1] = WATER;
//                                strNew[k][p + 1] = WATER;
//                                strNew[k - 1][p] = WATER;
//                            } else if(k > 0 && k < str.length - 1 && p > 0 && p < str[k].length-1){
//                                strNew[k][p] = WATER;
//                                strNew[k - 1][p] = WATER;
//                                strNew[k][p + 1] = WATER;
//                                strNew[k + 1][p] = WATER;
//                                strNew[k][p - 1] = WATER;
//                            }
//                        }
//                    }
//                }
//
//                String floodState = "";
//
//                for (int m = 0; m < strNew.length; m++){
//                    for(int n =0 ; n < strNew[m].length; n++){
//                        floodState += strNew[m][n];
//                    }
//                      if(m < strNew.length - 1){
//                          floodState += "\n";
//                      }
//                }
//
//                FloodLogger floodLogger = new FloodLogger() {
//                    @Override
//                    public void log(String floodState) {
//                        System.out.println(floodState + "\n");
//                    }
//                };
//
//                if(floodState.contains(String.valueOf(LAND))){
//                    flood(floodState, floodLogger);
//                }
//                else{
//                    System.out.println(floodState);
//                }
//            };
//        };


