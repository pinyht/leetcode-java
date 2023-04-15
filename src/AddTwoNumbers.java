/**
 * 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class AddTwoNumbers {
    // 链表结构,不要提交
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            String result = "";
            ListNode cur = this;
            while (cur != null) {
                result += " " + cur.val;
                cur = cur.next;
            }
            return !result.isEmpty() ? result : null;
        }
    }

    // 打败100%, 并且代码比较精简
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 排除无效条件
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode head = new ListNode(); // 用来保存计算的结果
        ListNode cur = head; // 指向当前处理接地那
        ListNode last = head; // 指向上一个节点
        int carry = 0; // 进位信息
        int value = 0; // 节点的值
        int sum = 0; // 节点之和
        int v1 = 0; // l1节点的值
        int v2 = 0; // l2节点的值

        // 当l1或l2没有都遍历完的情况
        while (l1 != null || l2 != null) {
            v1 = l1 != null ? l1.val : 0; // 当l1还存在就取l1的值
            v2 = l2 != null ? l2.val : 0; // 当l2还存在就去l2的值
            sum = (v1 + v2 + carry); // 计算节点之和,加上进位值
            value = sum % 10; // 计算节点应该存储的值
            carry = sum >= 10 ? 1 : 0; // 当节点之和大于10,进行进位标记
            cur.val = value;
            cur.next = new ListNode(); // 先初始化下一个节点,让下一次循环可以赋值
            last = cur; // 保存当前节点信息,在最后处理进位的时候用来删除最后一个节点
            cur = cur.next; // 当前节点移动
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry > 0) {
            cur.val = carry; // 如果有进位信息,最后一个节点需要存在,值为进位标记值
        } else {
            last.next = null; // 如果没有进位信息,需要删掉最后一个节点,不然会多一位0节点
        }
        return head;
    }

    // 打败100%,但是代码略冗余
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 排除无效条件
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode tmp = new ListNode();
        ListNode result = tmp;
        int carry = 0; // 进位标记
        int sum = 0;

        // 当l1和l2都没有遍历完
        while (l1 != null && l2 != null) {
            sum = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            tmp.val = sum;
            if (l1.next == null && l2.next == null && carry == 0) {
                tmp.next = null;
            } else {
                tmp.next = new ListNode();
            }
            l1 = l1.next;
            l2 = l2.next;
            tmp = tmp.next;
        }

        // 当l1结束,l2没有结束
        while (l1 == null && l2 != null) {
            sum = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;
            tmp.val = sum;
            tmp.next = l2.next;
            if (l2.next == null && carry > 0) {
                tmp.next = new ListNode();
            }
            l2 = l2.next;
            tmp = tmp.next;
        }

        // 当l1没有结束,l2结束
        while (l1 != null && l2 == null) {
            sum = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;
            tmp.val = sum;
            tmp.next = l1.next;
            if (l1.next == null && carry > 0) {
                tmp.next = new ListNode();
            }
            l1 = l1.next;
            tmp = tmp.next;
        }

        // 当l1和l2都结束
        while (l1 == null && l2 == null && carry > 0) {
            sum = carry % 10;
            carry = carry / 10;
            tmp.val = sum;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        System.out.println(new AddTwoNumbers().addTwoNumbers(l1, l2));
        l1 = new ListNode(9, new ListNode(9,
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        System.out.println(new AddTwoNumbers().addTwoNumbers(l1, l2));
    }
}
