package modal.tree;

import modal.Objects.Station;

class Node {
    Station station;
    int key;
    Node left;
    Node right;
    int height;
    int count;
}

public class AVLTree {

    public Node root;

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private void treeLevelPrinter(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.station.getId() + " ");
        } else if (level > 1) {
            treeLevelPrinter(root.left, level - 1);
            treeLevelPrinter(root.right, level - 1);
        }
    }

    public void print() {
        System.out.println("order of the tree is : ");

        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            treeLevelPrinter(root, i);
            System.out.print(System.lineSeparator());
        }

        System.out.println("");
    }

    private Node newNode(int key, Station station) {
        Node node = new Node();
        node.station = station;
        node.key = key;
        node.left = null;
        node.right = null;
        node.height = 1; // new Node is initially added at leaf
        node.count = 1;
        return (node);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private Node insert(Node node, int key, Station station) {

        if (node == null)
            return (newNode(key, station));

        if (key == node.key) {
            (node.count)++;
            return node;
        }

        if (key < node.key)
            node.left = insert(node.left, key, station);
        else
            node.right = insert(node.right, key, station);

        node.height = max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) Node pointer */
        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;

        /** loops down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }


    private Node searcCode(Node node, String code) {
        if (node == null || node.station.getCode().equalsIgnoreCase(code)) {
            return node;
        }

        Node left = searcCode(node.left, code);
        Node right = searcCode(node.right, code);

        return (left != null) ? left : right;
    }

    public Station searcCode(String code) {
        Node found = searcCode(root, code);
        System.out.println("found the name " + found.station.getName_long());
        return found.station;
    }

    private Node searchName(Node node, String name) {
        if (node == null || node.station.getName_long().equalsIgnoreCase(name)) {
            return node;
        }

        Node left = searchName(node.left, name);
        Node right = searchName(node.right, name);

        return (left != null) ? left : right;
    }

    public Station searchName(String name) {
        Node found = searchName(root, name);
//        System.out.println("found the name " + found.station.getName_long());
        return found.station;
    }

    private Node searchID(Node node, int key) {
        if (node == null || node.key == key) {
//            System.out.println("found " + node.station.getName_long());
            return node;
        }
        if (key < node.key) {
            return searchID(node.left, key);
        } else {
            return searchID(node.right, key);
        }
    }

    public Station searchID(int key) {
        Node found = searchID(root, key);
        System.out.println("searching for " + key);
        System.out.println("root is " + root.key);
        System.out.println("height is " + root.height);
        System.out.println("found the name " + found.station.getName_long());
        return found.station;
    }


    public Node deleteNode(Node root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);

        else if (key > root.key)
            root.right = deleteNode(root.right, key);

        else {

            if (root.count > 1) {
                (root.count)--;
                return null;
            }

            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp; //
            } else {

                Node temp = minValueNode(root.right);

                root.key = temp.key;
                root.count = temp.count;
                temp.count = 1;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;


        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        /** Left Left Case */
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        /** Left Right Case */
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        /** Right Right Case */
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        /** Right Left Case */
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void addTree(AVLTree tree, Station key) {
        int keyInt = key.getId();
        tree.root = tree.insert(tree.root, keyInt, key);
    }


}


