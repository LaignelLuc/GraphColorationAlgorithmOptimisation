/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author jpmelisse
 */
public class AlgoSorting implements Algorithm {

    @Override
    public Object apply(IGraph g, Map parameters) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Take an ArrayList of Integer and return it to 
    // a decreasing order (Variant of selection sort)
    public static void getDecreasingOrder(IGraph g, ArrayList<INode> nodes){
        
        int indexI, indexMax, maxLocal;
        // We loop in the arrayList
        for (int i = 0; i < nodes.size()-1; i++) {
            
            // Initiation of values
            indexI = i;
            maxLocal = g.degree(nodes.get(i));
            indexMax = -1;
            
            // We look again the arrayList from index indexI+1
            for (int j = indexI+1; j < nodes.size(); j++) {
                
                if (g.degree(nodes.get(j)) > maxLocal) {
                    maxLocal = g.degree(nodes.get(j));
                    indexMax = j;
                }
            }
            // In the end, we exchange i and maxLocal
            if (indexMax != -1) {
                INode n = nodes.get(indexMax);
                nodes.set(indexMax, nodes.get(indexI));
                nodes.set(indexI, n);         
            }
        }
        
    }
    
    // Merge sort algorithm (from Cycle 5)
    
    /**
     * Tri fusion, restreint sur un intervalle.
     *
     * @param tab tableau à trier
     * @param debut indice du début de l'intervalle
     * @param fin indice de la fin de l'intervalle
     * @return le nombre d'opérations durant ce tri
     */
    static long triFusionPartiel(int[] tab, int debut, int fin) {
        long nbOperations = 1; // coût du test fin > debut
        if (fin > debut) {
            int milieu = (debut + fin) / 2;
            nbOperations += triFusionPartiel(tab, debut, milieu);
            nbOperations += triFusionPartiel(tab, milieu + 1, fin);
            nbOperations += fusion(tab, debut, milieu, fin);
        }
        return nbOperations;
    }

    /**
     * Action de fusion de deux intervalles triés.
     *
     * @param tab tableau à trier
     * @param debut indice de début
     * @param milieu indice séparant les deux parties (fin du premier
     * intervalle)
     * @param fin indice de fin
     * @return le nombre d'opérations durant ce tri
     */
    static long fusion(int[] tab, int debut, int milieu, int fin) {
        long nbOperations = 0;
        int tmp[] = new int[tab.length];
        int posGauche = debut;
        int posDroite = milieu + 1;
        int posTmp = debut;
        // on compare jusqu'à la fin d'un des deux intervalles
        while (posGauche <= milieu && posDroite <= fin) {
            nbOperations += 3; // 2 comparaisons ci-dessus et une ci-dessous
            if (tab[posGauche] < tab[posDroite]) {
                tmp[posTmp] = tab[posGauche];
                posGauche++;
                nbOperations++; // échange ci-dessus
            } else {
                tmp[posTmp] = tab[posDroite];
                posDroite++;
                nbOperations++; // échange ci-dessus
            }
            posTmp++;
        }
        // on vide le premier intervalle
        while (posGauche <= milieu) {
            nbOperations += 2; // comparaison ci-dessus + échange ci-dessous
            tmp[posTmp] = tab[posGauche];
            posGauche++;
            posTmp++;
        }
        // on vide le second intervalle
        while (posDroite <= fin) {
            nbOperations += 2; // comparaison ci-dessus + échange ci-dessous
            tmp[posTmp] = tab[posDroite];
            posDroite++;
            posTmp++;
        }
        // on recopie tmp dans tab
        for (int i = debut; i <= fin; i++) {
            nbOperations++;
            tab[i] = tmp[i];
        }
        return nbOperations;
    }

    /**
     * Tri fusion.
     *
     * @param tab tableau à trier
     * @return le nombre d'opérations durant ce tri
     */
    static long triFusion(int[] tab) {
        return triFusionPartiel(tab, 0, tab.length - 1);
    }
    
}
