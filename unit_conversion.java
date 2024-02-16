
import java.util.ArrayList;

public class unit_conversion {

    static class Node {
        double value;
        String name;
        Node next;
        Node prev;

        Node() {
            value = -1;
            name = "";
            next = null;
            prev = null;
        }

        Node(double value, String name) {
            this.value = value;
            this.name = name;
        }

    }

    static class NodeList {
        Node head;
        Node tail;

        NodeList() {
            this.head = new Node();
            this.tail = new Node();
            head.next = tail;
            head.prev = null;
            tail.next = null;
            tail.prev = head;

        }

        void setHead(double value, String name) {
            Node newNode = new Node(value, name);
            tail = new Node();
            newNode.next = tail;
            newNode.prev = null;
            tail.next = null;
            tail.prev = head;

        }

        void addNode(Node newNode) {
            Node temp = head;
            if (temp.value == -1) { // no head
                this.head = newNode;
                newNode.next = this.tail;
                newNode.prev = null;
                return;
            }
            while (temp.next != tail) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
            newNode.next = tail; // last node
            tail.prev = newNode;

        }

        double find_converstion(NodeList list, double unit1_val, String unit1_name, String unit2_name) {

            Node ptr = list.head;
            double val1 = -1;
            double val2 = -1;
            double result = -1;
            boolean mutl = true;

            while (ptr != tail) {
                if (ptr.name.equals(unit1_name)) {
                    val1 = ptr.value;
                    break;
                }
                ptr = ptr.next;
            }
            Node ptr2 = ptr;
            while (ptr != tail) {
                if (ptr.name.equals(unit2_name)) {
                    val2 = ptr.value;
                    result = val2 / val1;
                    
                }
                ptr = ptr.next;
            }
            if (val2 == -1) { // didn't find it forward. Will have to use prev
                mutl = false;
                // if not found then return and -1
                ptr = tail.prev;
                while (ptr != null) {
                    if (ptr.name.equals(unit2_name)) {
                        val2 = ptr.value;
                        // if(ptr.prev == ptr2){ //check if right next to ptr2
                        result = val1 / val2;
                        // }
                    }
                    ptr = ptr.prev;
                }

            }

            if ((val1 != -1 && val2 != -1) && (mutl == true)) { // mult
                return (unit1_val * result);
            } else if ((val1 != -1 && val2 != -1)) {
                return (unit1_val / result);

            }

            // System.out.println("done with loop");
            return -1;

        }

    }

    public static void main(String[] args) {
        ArrayList<NodeList> all_conversions = new ArrayList<>();

        NodeList size = new NodeList();
        size.addNode(new Node(1, "m"));
        size.addNode(new Node(3.28, "ft"));
        size.addNode(new Node(39.36, "in"));
        all_conversions.add(size);

        NodeList time = new NodeList();
        time.addNode(new Node(1, "hr"));
        time.addNode(new Node(60, "min"));
        time.addNode(new Node(3600, "sec"));
        all_conversions.add(time);

        double unit1 = 1;
        String unit1_Name = "min";
        String unit2_Name = "sec";
        for (int i = 0; i < all_conversions.size(); i++) {
            NodeList list = all_conversions.get(i);
            if(list.find_converstion(list, unit1, unit1_Name, unit2_Name) == -1){
                System.out.println("Conversion not allowed");
            }
            else{
                System.out.println("final output : " + list.find_converstion(list, unit1, unit1_Name, unit2_Name));
         }

    }
}
}