/**
 * N 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 题目链接: https://leetcode.cn/problems/zigzag-conversion/
 */
public class Convert {
    // 打败100%,通过N字型规律直接计算下标输出结果
    public String convert(String s, int numRows) {
        // 当s的长度小于numRows,则第一列就可以存储完所有数据,输出的顺序不变,当numRows等于1,只有一行数据,输出顺序也不变,直接返回
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        // 将字符串转换为字符数组,效率更高
        char[] arr = s.toCharArray();
        int arrLen = arr.length;
        // 初始化新的数组,用作按照结果的顺序输出,长度为原始字符串长度
        char[] c = new char[arrLen];
        int pos = 0; // 新数组c的初始下标
        // 按行遍历
        for (int i = 0; i < numRows; i++) {
            int j = i;  // 用j暂存,下标的计算都在j上进行,保证外层循环每一次都是第一列的下标
            if (i == 0 || i == numRows - 1) {
                // 当在第一行或者最后一行,不需要分上半区和下半区计算,可以用统一的计算公式
                while (j < arrLen) {
                    c[pos++] = arr[j];  // 先存储第一列下标对应的字符,再进行计算迭代
                    j += (numRows - 1) * 2; // 从第二列开始,N字型需要从当前下标的后面一个元素开始走一个来回
                }
            } else {
                // 中间行的情况,需要分为上半区和下半区两种情况分别计算
                while (j < arrLen) {
                    c[pos++] = arr[j];  // 先存储第一列下标对应的字符,再进行计算迭代,后续就是上半区走一个来回后的下标
                    // 下半区计算,用当前列的长度减去已经走过的元素个数再减去自身,就是当前列剩下的元素个数
                    // 下半区需要走一个来回,直接*2就是该行下一个元素的坐标
                    j += (numRows - i - 1) * 2; 
                    if (j >= arrLen) {
                        break;
                    }
                    c[pos++] = arr[j];  // 存储计算出的下半区坐标对应的字符
                    j += i * 2;     // 上半区计算,i的下标就是当前行向下的偏移量,上半区走一个来回就是偏移量*2,得出该行下一个字符坐标
                }
            }
        }
        return new String(c);
    }

    // 效率低
    public String convert2(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        int arrLen = arr.length;
        String[] ss = new String[numRows];
        int c = 0;
        while (c < arrLen) {
            for (int i = 0; i < numRows; i++) {
                if (c >= arrLen) {
                    break;
                }
                ss[i] = ss[i] == null ? String.valueOf(arr[c]) : ss[i] + arr[c];
                c++;
            }
            for (int j = numRows - 2; j > 0;) {
                if (c >= arrLen) {
                    break;
                }
                ss[j] = ss[j] == null ? String.valueOf(arr[c]) : ss[j] + arr[c];
                j--;
                c++;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            result.append(ss[i]);
        }
        return result.toString();
    }

    // 效率极低
    public String convert1(String s, int numRows) {
        if (s.length() == numRows || numRows == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        int arrLen = arr.length;
        char[][] table = new char[numRows][arrLen];
        int c = 0;
        int row = 0;
        while (c < arrLen) {
            for (int i = 0; i < numRows; i++) {
                if (c >= arrLen) {
                    break;
                }
                table[i][row] = arr[c];
                c++;
            }
            for (int j = numRows - 2; j > 0;) {
                if (c >= arrLen) {
                    break;
                }
                table[j--][++row] = arr[c];
                c++;
            }
            row++;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] != 0) {
                    result.append(table[i][j]);
                }
            }
        }
        return result.toString();
    }

    public void printArr(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Convert().convert("PAYPALISHIRING", 3));
        System.out.println(new Convert().convert("PAYPALISHIRING", 4));
        System.out.println(new Convert().convert("A", 2));
    }
}
