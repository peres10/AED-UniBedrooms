package dataStructures;

public class BSTKeyOrderIterator <K,V> implements Iterator<Entry<K,V>>{
    //Avg case: O(1)
    //Worst case: O(log n)
    //Best case: O(1)
    Stack<BSTNode<K,V>> nextNodesStack;
    BSTNode<K,V> treeRoot;

    //Avg case: O(log n)
    //Best case: O(1)
    //Worst case: O(n)
    public BSTKeyOrderIterator(BSTNode<K,V> root){
        nextNodesStack = new StackInList<>();
        this.treeRoot = root;
        pushLeftNodes(treeRoot);
    }

    //O(1)
    @Override
    public boolean hasNext() {
        return !nextNodesStack.isEmpty();
    }

    //Avg case: O(1)
    //Worst case: O(n)
    //Best case: O(1)
    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if ( !this.hasNext() )
            throw new NoSuchElementException();
        BSTNode<K,V> next = nextNodesStack.pop();
        if(next.getRight()!=null){
            BSTNode<K,V> leftNode=next.getRight();
            pushLeftNodes(leftNode);
        }

        return next.getEntry();
    }

    @Override
    public void rewind() {
        while(!nextNodesStack.isEmpty())
            nextNodesStack.pop();
        pushLeftNodes(treeRoot);
    }

    private void pushLeftNodes(BSTNode<K,V> branchRoot){
        while(branchRoot != null){
            nextNodesStack.push(branchRoot);
            branchRoot=branchRoot.getLeft();
        }
    }
}
