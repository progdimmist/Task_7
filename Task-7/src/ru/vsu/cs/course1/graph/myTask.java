package ru.vsu.cs.course1.graph;

import java.util.*;
import java.util.function.Consumer;

public class myTask {

    public static LinkedList<Integer> task(Graph graph, int from,int to, int[] array) {
        Queue<Integer> queue = new LinkedList<Integer>();
        LinkedList<LinkedList<Integer>> arr = new LinkedList<LinkedList<Integer>>();
        LinkedList<Integer> list;
        queue.add(from);
        int k = 0;
        while (queue.size() > 0) {//находим все связи и записываем в массив
            list = new LinkedList();
            list.add(queue.element());

            Integer curr = queue.remove();
            for (Integer v : graph.adjacencies(curr)) {
                if (k < 100) {
                    queue.add(v);
                    list.add(v);
                    k++;
                }
            }
            arr.add(list);
        }
        for (int i = 0; i < arr.size(); i++) {//удаляем списки, в вершинах которых нет связей
            if ((arr.get(i).size() == 1) || (arr.get(i).get(0) == 2)) {
                arr.remove(i);
                i--;
            }
        }
        for (int i = 0; i < arr.size(); i++) {//удаляем списки с одинаковыми вершинами
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).get(0) == arr.get(j).get(0)) {
                    arr.remove(j);
                    j--;
                }
            }
        }
        for (int i = 0; i < arr.size(); i++) {//удаляем вершины, которые надо обойти
            for (int j = 1; j < arr.get(i).size(); j++) {
                for (int l = 0; l < array.length; l++) {
                    if(arr.get(i).get(0) == array[l]){
                        arr.remove(i);
                    }else if (arr.get(i).get(j) == array[l]) {
                        arr.get(i).remove(j);
                    }
                }
            }
        }
        LinkedList<Integer> lastList=new LinkedList<>();
        lastList.add(0);
        for (int i = 0; i < arr.size(); i++) {//из связей делаем итоговый список
            for (int j = 1; j < arr.get(i).size()+1; j++) {
                if (arr.get(i).get(j)==to ){
                    lastList.add(arr.get(i).get(0));
                }
                break;
            }
        }
        lastList.add(to);
        return lastList;
    }

}
