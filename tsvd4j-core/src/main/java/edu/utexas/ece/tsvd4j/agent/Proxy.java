package edu.utexas.ece.tsvd4j.agent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.Set;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Proxy {

    static Utility util;

    public Proxy() {
        util = new Utility();
    }

    public static void setVariables(String name, int lineNumber, String className) {
        InterceptionPoint interception = Helper.createInstance(name, className, "", lineNumber, Operation.WRITE);
        util.onCall(interception);
    }

    public static void readVariables(String name, int lineNumber, String className) {
        InterceptionPoint interception = Helper.createInstance(name, className, "", lineNumber, Operation.READ);
        util.onCall(interception);
    }

    public static void getFieldVariables(Object obj, String name, int lineNumber, String className) {
        InterceptionPoint interception = Helper.createInstance(obj, name, className, "", lineNumber, Operation.READ);
        util.onCall(interception);
    }

    public static void putFieldVariables(Object obj, String name, int lineNumber, String className) {
        InterceptionPoint interception = Helper.createInstance(obj, name,  className,"", lineNumber, Operation.WRITE);
        util.onCall(interception);
    }

    //Collection
    public static boolean add(Collection collection, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return collection.add(obj);
    }

    public static boolean addAll(Collection collection, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return collection.addAll(obj);
    }

    public static void clear(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        collection.clear();
    }

    public static boolean contains(Collection collection, Object obj,  int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.contains(obj);
    }

    public static boolean containsAll(Collection collection, Collection obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.containsAll(obj);
    }

    public static boolean equals(Collection collection, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.equals(obj);
    }

    public static int hashCode(Collection collection, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.hashCode();
    }

    public static boolean isEmpty(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.isEmpty();
    }

    public static Iterator iterator(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.iterator();
    }

    public static Stream parallelStream(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.parallelStream();
    }

    public static boolean remove(Collection collection, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return collection.remove(obj);
    }

    public static boolean removeAll(Collection collection, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return collection.removeAll(obj);
    }

    public static boolean removeIf(Collection collection, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return collection.removeIf(p);
    }

    public static boolean retainAll(Collection collection, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return collection.retainAll(c);
    }

    public static int size(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.size();
    }

    public static Spliterator spliterator(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.spliterator();
    }

    public static Stream stream(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.stream();
    }

    public static Object[] toArray(Collection collection, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.toArray();
    }

    public static Object[] toArray(Collection collection, Object[] obj,  int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(collection, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return collection.toArray(obj);
    }

    //List
    public static boolean add(List list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.add(obj);
    }

    public static void add(List list, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.add(index, obj);
    }

    public static boolean addAll(List list, Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.addAll(collections);
    }

    public static boolean addAll(List list, int index,  Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.addAll(index, collections);
    }

    public static void clear(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.clear();
    }

    public static boolean contains(List list, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.contains(obj);
    }

    public static boolean containsAll(List list, Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.containsAll(collections);
    }

    public static boolean equals(List list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.equals(obj);
    }

    public static Object get(List list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.get(index);
    }

    public static int hashCode(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.hashCode();
    }

    public static int indexOf(List list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.indexOf(obj);
    }

    public static boolean isEmpty(List list, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.isEmpty();
    }

    public static Iterator iterator(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.iterator();
    }

    public static int lastIndexOf(List list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.lastIndexOf(obj);
    }

    public static ListIterator listIterator(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.listIterator();
    }

    public static ListIterator listIterator(List list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.listIterator(index);
    }

    public static Stream parallelStream(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.parallelStream();
    }

    public static Object remove(List list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.remove(index);
    }

    public static boolean remove(List list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.remove(obj);
    }

    public static boolean removeAll(List list, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.removeAll(c);
    }

    public static boolean removeIf(List list, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.removeIf(p);
    }

    public static void replaceAll(List list, UnaryOperator operator, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.replaceAll(operator);
    }

    public static boolean retainAll(List list, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.retainAll(c);
    }

    public static Object set(List list, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.set(index, obj);
    }

    public static int size(List list, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.size();
    }

    public static void sort(List list, Comparator c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.sort(c);
    }

    public static Spliterator spliterator(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.spliterator();
    }

    public static List subList(List list, int from, int to, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.subList(from, to);
    }

    public static Stream stream(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.stream();
    }

    public static Object[] toArray(List list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.toArray();
    }

    public static Object[] toArray(List list, Object[] obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.toArray(obj);
    }

    //ArrayList
    public static boolean add(ArrayList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.add(obj);
    }

    public static void add(ArrayList list, int index,  Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.add(index, obj);
    }

    public static boolean addAll(ArrayList list, Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.addAll(collections);
    }

    public static boolean addAll(ArrayList list, int index, Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.addAll(index, collections);
    }

    public static void clear(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.clear();
    }

    public static Object clone(ArrayList list, int lineNumber, String methodName, String className){  // Didn't add this in API
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.clone();
    }

    public static boolean contains(ArrayList list, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.contains(obj);
    }

    public static boolean containsAll(ArrayList list, Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.containsAll(collections);
    }

    public static boolean equals(ArrayList list, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.equals(obj);
    }

    public static void ensureCapacity(ArrayList list, int size, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.ensureCapacity(size);
    }

    public static void forEach(ArrayList list, Consumer obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        list.forEach(obj);
    }

    public static Object get(ArrayList list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.get(index);
    }

    public static int hashCode(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.hashCode();
    }

    public static int indexOf(ArrayList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.indexOf(obj);
    }

    public static boolean isEmpty(ArrayList list,  int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.isEmpty();
    }

    public static Iterator iterator(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.iterator();
    }

    public static int lastIndexOf(ArrayList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.lastIndexOf(obj);
    }

    public static ListIterator listIterator(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.listIterator();
    }

    public static ListIterator listIterator(ArrayList list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.listIterator(index);
    }

    public static Stream parallelStream(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.parallelStream();
    }

    public static boolean remove(ArrayList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.remove(obj);
    }

    public static Object remove(ArrayList list, int item, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.remove(item);
    }

    public static boolean removeAll(ArrayList list, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.removeAll(c);
    }

    public static boolean removeIf(ArrayList list, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.removeIf(p);
    }

    public static void replaceAll(ArrayList list, UnaryOperator operator, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.replaceAll(operator);
    }

    public static boolean retainAll(ArrayList list, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.retainAll(c);
    }

    public static Object set(ArrayList list, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.set(index, obj);
    }

    public static int size(ArrayList list,  int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.size();
    }

    public static void sort(ArrayList list, Comparator c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.sort(c);
    }

    public static Spliterator spliterator(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.spliterator();
    }

    public static List subList(ArrayList list, int from, int to, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.subList(from, to);
    }

    public static Stream stream(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.stream();
    }

    public static Object[] toArray(ArrayList list, Object[] obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.toArray(obj);
    }

    public static Object[] toArray(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.toArray();
    }

    public static void trimToSize(ArrayList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.trimToSize();
    }

    //LinkedList
    public static boolean add(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.add(obj);
    }

    public static void add(LinkedList list, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.add(index, obj);
    }

    public static boolean addAll(LinkedList list, Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.addAll(collections);
    }

    public static boolean addAll(LinkedList list, int index,  Collection collections, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.addAll(index, collections);
    }

    public static void addFirst(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.addFirst(obj);
    }

     public static void addLast(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.addLast(obj);
    }

    public static void clear(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.clear();
    }

    public static boolean contains(LinkedList list, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.contains(obj);
    }

    public static boolean containsAll(LinkedList list, Collection collections, int lineNumber, String methodName, String className) { // Not added in API.txt
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.containsAll(collections);
    }

    public static Object clone(LinkedList list, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.clone();
    }

    public static Object element(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.element();
    }

    public static boolean equals(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.equals(obj);
    }

    public static Object get(LinkedList list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.get(index);
    }

    public static Object getFirst(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.getFirst();
    }

    public static Object getLast(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.getLast();
    }

    public static int hashCode(LinkedList list, int lineNumber, String methodName,  String className) { // Didn't add in API.txt
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.hashCode();
    }

    public static int indexOf(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.indexOf(obj);
    }

    public static boolean isEmpty(LinkedList list, int lineNumber, String methodName, String className) { // Didn't add in API.txt
       InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
       util.onCall(interception);
       return list.isEmpty();
    }

    public static Iterator iterator(LinkedList list, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.iterator();
    }

    public static int lastIndexOf(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.lastIndexOf(obj);
    }

    public static ListIterator listIterator(LinkedList list, int lineNumber, String methodName,  String className) {  // Didn't add in API.txt
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.listIterator();
    }

    public static ListIterator listIterator(LinkedList list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.listIterator(index);
    }

    public static Stream parallelStream(LinkedList list, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.parallelStream();
    }

    public static Object peek(LinkedList list, int lineNumber, String methodName,  String className) {// Didn't add this in API
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.peek();
    }

    public static Object peekFirst(LinkedList list, int lineNumber, String methodName,  String className) { // Didn't add this in API
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.peekFirst();
    }

    public static Object peekLast(LinkedList list, int lineNumber, String methodName,  String className) { // Didn't add this in API
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.peekLast();
    }

     public static boolean remove(LinkedList list, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.remove(obj);
    }

     public static Object remove(LinkedList list, int index, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.remove(index);
    }

     public static boolean removeAll(LinkedList list, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.removeAll(c);
    }

    public static boolean removeIf(LinkedList list, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.removeIf(p);
    }

    public static void replaceAll(LinkedList list, UnaryOperator operator, int lineNumber, String methodName,  String className) { // Didn't add in API.txt
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.replaceAll(operator);
    }

    public static boolean retainAll(LinkedList list, Collection c, int lineNumber, String methodName,  String className) { // Didn't add in API.txt
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.retainAll(c);
    }

    public static Object set(LinkedList list, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return list.set(index, obj);
    }

    public static int size(LinkedList list,  int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.size();
    }

    public static void sort(LinkedList list, Comparator c, int lineNumber, String methodName,  String className) { // Didn't add in API.txt
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        list.sort(c);
    }

    public static Spliterator spliterator(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.spliterator();
    }

    public static Stream stream(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.stream();
    }

    public static List subList(LinkedList list, int from, int to, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.subList(from, to);
    }

    public static Object[] toArray(LinkedList list, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.toArray();
    }

    public static Object[] toArray(LinkedList list, Object[] obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(list, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return list.toArray(obj);
    }

    //Map
    public static void clear(Map map, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.clear();
    }

    public static Object compute(Map map, Object key, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.compute(key, fnc);
    }

    public static Object computeIfAbsent(Map map, Object key, Function fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.computeIfAbsent(key, fnc);
    }

    public static Object computeIfPresent(Map map, Object key, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.computeIfPresent(key, fnc);
    }

    public static boolean containsKey(Map map, Object obj, int lineNumber, String methodName, String className) {
       InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
       util.onCall(interception);
       return map.containsKey(obj);
    }

    public static boolean containsValue(Map map, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.containsValue(obj);
    }

    public static Set entrySet(Map map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.entrySet();
    }

    public static void forEach(Map map, BiConsumer obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        map.forEach(obj);
    }

    public static Object get(Map map, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.get(obj);
    }

    public static Object getOrDefault(Map map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.getOrDefault(key, value);
    }

    public static boolean isEmpty(Map map, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.isEmpty();
    }

    public static Set keySet(Map map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.keySet();
    }

    public static Object merge(Map map, Object key, Object value,  BiFunction fnc, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.merge(key, value, fnc);
    }

    public static Object put(Map map, Object obj1, Object obj2, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.put(obj1, obj2);
    }

    public static void putAll(Map map, Map map1, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.putAll(map1);
    }

    public static Object putIfAbsent(Map map, Object obj1, Object obj2, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.putIfAbsent(obj1, obj2);
    }

    public static Object remove(Map map, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.remove(obj);
    }

    public static boolean remove(Map map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.remove(key, value);
    }

    public static boolean replace(Map map, Object key, Object oldValue, Object newValue, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.replace(key, oldValue, newValue);
    }

    public static Object replace(Map map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.replace(key, value);
    }

    public static void replaceAll(Map map, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.replaceAll(fnc);
    }

    public static int size(Map map,  int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.size();
    }

    public static Collection values(Map map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.values();
    }

    //HashMap
    public static void clear(HashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.clear();
    }

    public static Object compute(HashMap map, Object key, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.compute(key, fnc);
    }

    public static Object computeIfAbsent(HashMap map, Object key, Function fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.computeIfAbsent(key, fnc);
    }

    public static Object computeIfPresent(HashMap map, Object key, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.computeIfPresent(key, fnc);
    }

    public static boolean containsKey(HashMap map, Object obj, int lineNumber, String methodName, String className) {
       InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
       util.onCall(interception);
       return map.containsKey(obj);
    }

    public static boolean containsValue(HashMap map, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.containsValue(obj);
    }

    public static Set entrySet(HashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.entrySet();
    }

    public static void forEach(HashMap map, BiConsumer obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        map.forEach(obj);
    }

    public static Object get(HashMap map, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.get(obj);
    }

    public static Object getOrDefault(HashMap map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.getOrDefault(key, value);
    }

    public static boolean isEmpty(HashMap map, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.isEmpty();
    }

    public static Set keySet(HashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.keySet();
    }

    public static Object merge(HashMap map, Object key, Object value,  BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.merge(key, value, fnc);
    }

    public static Object put(HashMap map, Object obj1, Object obj2, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.put(obj1, obj2);
    }

    public static void putAll(HashMap map, HashMap map1, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.putAll(map1);
    }

    public static Object putIfAbsent(HashMap map, Object obj1, Object obj2, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.putIfAbsent(obj1, obj2);
    }

    public static Object remove(HashMap map, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.remove(obj);
    }

    public static boolean remove(HashMap map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.remove(key, value);
    }

    public static boolean replace(HashMap map, Object key, Object oldValue, Object newValue, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.replace(key, oldValue, newValue);
    }

    public static Object replace(HashMap map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.replace(key, value);
    }

    public static void replaceAll(HashMap map, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.replaceAll(fnc);
    }

    public static int size(HashMap map,  int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.size();
    }

    public static Collection values(HashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.values();
    }

    //LinkedHashMap
    public static void clear(LinkedHashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.clear();
    }

    public static Object compute(LinkedHashMap map, Object key, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.compute(key, fnc);
    }

    public static Object computeIfAbsent(LinkedHashMap map, Object key, Function fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.computeIfAbsent(key, fnc);
    }

    public static Object computeIfPresent(LinkedHashMap map, Object key, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.computeIfPresent(key, fnc);
    }

    public static boolean containsKey(LinkedHashMap map, Object obj, int lineNumber, String methodName, String className) {
       InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
       util.onCall(interception);
       return map.containsKey(obj);
    }

    public static boolean containsValue(LinkedHashMap map, Object obj, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.containsValue(obj);
    }

    public static Set entrySet(LinkedHashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.entrySet();
    }

    public static void forEach(LinkedHashMap map, BiConsumer obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        map.forEach(obj);
    }

    public static Object get(LinkedHashMap map, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.get(obj);
    }

    public static Object getOrDefault(LinkedHashMap map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.getOrDefault(key, value);
    }

    public static boolean isEmpty(LinkedHashMap map, int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.isEmpty();
    }

    public static Set keySet(LinkedHashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.keySet();
    }

    public static Object merge(LinkedHashMap map, Object key, Object value,  BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.merge(key, value, fnc);
    }

    public static Object put(LinkedHashMap map, Object obj1, Object obj2, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.put(obj1, obj2);
    }

    public static void putAll(LinkedHashMap map, HashMap map1, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.putAll(map1);
    }

    public static Object putIfAbsent(LinkedHashMap map, Object obj1, Object obj2, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.putIfAbsent(obj1, obj2);
    }

    public static Object remove(LinkedHashMap map, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.remove(obj);
    }

    public static boolean remove(LinkedHashMap map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.remove(key, value);
    }

    public static boolean replace(LinkedHashMap map, Object key, Object oldValue, Object newValue, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.replace(key, oldValue, newValue);
    }

    public static Object replace(LinkedHashMap map, Object key, Object value, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return map.replace(key, value);
    }

    public static void replaceAll(LinkedHashMap map, BiFunction fnc, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        map.replaceAll(fnc);
    }

    public static int size(LinkedHashMap map,  int lineNumber, String methodName, String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.size();
    }

    public static Collection values(LinkedHashMap map, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(map, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return map.values();
    }

    //Set
    public static boolean add(Set set, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.add(obj);
    }

    public static boolean addAll(Set set, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.addAll(obj);
    }

    public static void clear(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        set.clear();
    }

    public static boolean contains(Set set, Object obj,  int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.contains(obj);
    }

    public static boolean containsAll(Set set, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.containsAll(obj);
    }

    public static boolean equals(Set set, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.equals(obj);
    }

    public static int hashCode(Set set, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.hashCode();
    }

    public static boolean isEmpty(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.isEmpty();
    }

    public static Iterator iterator(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.iterator();
    }

    public static Stream parallelStream(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.parallelStream();
    }

    public static boolean remove(Set set, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.remove(obj);
    }

    public static boolean removeAll(Set set, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.removeAll(obj);
    }

    public static boolean removeIf(Set set, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.removeIf(p);
    }

    public static boolean retainAll(Set set, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.retainAll(c);
    }

    public static int size(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.size();
    }

    public static Spliterator spliterator(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.spliterator();
    }

    public static Stream stream(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.stream();
    }

    public static Object[] toArray(Set set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.toArray();
    }

    public static Object[] toArray(Set set, Object[] obj,  int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.toArray(obj);
    }


    //HashSet
    public static boolean add(HashSet set, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.add(obj);
    }

    public static boolean addAll(HashSet set, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.addAll(obj);
    }

    public static void clear(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        set.clear();
    }

    public static boolean contains(HashSet set, Object obj,  int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.contains(obj);
    }

    public static boolean equals(HashSet set, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.equals(obj);
    }

    public static int hashCode(HashSet set, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.hashCode();
    }

    public static boolean isEmpty(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.isEmpty();
    }

    public static Iterator iterator(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.iterator();
    }

    public static Stream parallelStream(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.parallelStream();
    }

    public static boolean remove(HashSet set, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.remove(obj);
    }

    public static boolean removeAll(HashSet set, Collection obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.removeAll(obj);
    }

    public static boolean removeIf(HashSet set, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.removeIf(p);
    }

    public static boolean retainAll(HashSet set, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return set.retainAll(c);
    }

    public static int size(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.size();
    }

    public static Spliterator spliterator(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.spliterator();
    }

    public static Stream stream(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.stream();
    }

    public static Object[] toArray(HashSet set, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.toArray();
    }

    public static Object[] toArray(HashSet set, Object[] obj,  int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(set, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return set.toArray(obj);
    }

    //Vector
    public static boolean add(Vector vector, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.add(obj);
    }

    public static void add(Vector vector, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        vector.add(index, obj);
    }

    public static boolean addAll(Vector  vector, Collection collections, int lineNumber, String methodName, String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.addAll(collections);
    }

    public static boolean addAll(Vector  vector, int index,  Collection collections, int lineNumber, String methodName, String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.addAll(index, collections);
    }

    public static void addElement(Vector  vector, Object obj, int lineNumber, String methodName, String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        vector.addElement(obj);
    }

    public static void clear(Vector vector, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        vector.clear();
    }

    public static int capacity(Vector vector, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.capacity();
    }

    public static boolean contains(Vector vector, Object obj, int lineNumber, String methodName, String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.contains(obj);
    }

    public static boolean containsAll(Vector vector, Collection collections, int lineNumber, String methodName, String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.containsAll(collections);
    }

    public static Object elementAt(Vector vector, int index, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.elementAt(index);
    }

    public static boolean equals(Vector vector, Object obj, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.equals(obj);
    }

    public static Object get(Vector vector, int index, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.get(index);
    }

    public static int hashCode(Vector vector, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.hashCode();
    }

    public static int indexOf(Vector vector, Object obj, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.indexOf(obj);
    }

     public static boolean isEmpty(Vector vector,  int lineNumber, String methodName, String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.isEmpty();
    }

    public static Iterator iterator(Vector vector, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.iterator();
    }

    public static int lastIndexOf(Vector vector, Object obj, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.lastIndexOf(obj);
    }

    public static ListIterator listIterator(Vector vector, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.listIterator();
    }

    public static ListIterator listIterator(Vector vector, int index, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.listIterator(index);
    }

    public static Stream parallelStream(Vector vector, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.parallelStream();
    }

    public static Object remove(Vector vector, int index, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.remove(index);
    }

    public static boolean remove(Vector vector, Object obj, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.remove(obj);
    }
     
    public static boolean removeAll(Vector vector, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.removeAll(c);
    }

    public static boolean removeIf(Vector vector, Predicate p, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.removeIf(p);
    }

    public static void replaceAll(Vector vector, UnaryOperator operator, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        vector.replaceAll(operator);
    }

    public static boolean retainAll(Vector vector, Collection c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.retainAll(c);
    }

    public static Object set(Vector vector, int index, Object obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        return vector.set(index, obj);
    }

    public static int size(Vector vector, int lineNumber, String methodName,  String className){
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.size();
    }

    public static void sort(Vector vector, Comparator c, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.WRITE);
        util.onCall(interception);
        vector.sort(c);
    }

    public static Spliterator spliterator(Vector vector, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.spliterator();
    }

    public static List subList(Vector vector, int from, int to, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.subList(from, to);
    }

    public static Stream stream(Vector vector, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.stream();
    }

    public static Object[] toArray(Vector vector, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.toArray();
    }

    public static Object[] toArray(Vector vector, Object[] obj, int lineNumber, String methodName,  String className) {
        InterceptionPoint interception = Helper.createInstance(vector, className, methodName, lineNumber, Operation.READ);
        util.onCall(interception);
        return vector.toArray(obj);
    }
}
