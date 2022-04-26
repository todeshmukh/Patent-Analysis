package patentanalysis;

import java.util.Arrays;
import java.util.List;

/**
 * @author teamTechnowings
 */
public class TfIdfHelper {

	public double getTF(List<String> doc, String term) {
		double result = 0;
		for (String word : doc) {
			if (term.equalsIgnoreCase(word))
				result++;
		}
		System.out.println("TF "+result / doc.size());
		return result / doc.size();
	}

	public double getIDF(List<List<String>> docs, String term) {
		double n = 0;
		for (List<String> doc : docs) {
			for (String word : doc) {
				if (term.equalsIgnoreCase(word)) {
					n++;
					break;
				}
			}
		}
		System.out.println("IDF "+Math.log(docs.size() / n));
		return Math.log(docs.size() / n);
	}

	public double getTfIdf(List<String> doc, List<List<String>> docs, String term) {
		return getTF(doc, term) * getIDF(docs, term);

	}

	public static void main(String[] args) {

		List<String> doc1 = Arrays.asList("Hey", "computer", "dolor", "Divine",
				"dolor", "hey");
		List<String> doc2 = Arrays.asList("programming", "corporate", "at",
				"programming", "pro", "dual");
		List<String> doc3 = Arrays.asList("computer", "dual", "gogle",
				"computer", "hi");
		List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

		TfIdfHelper calculator = new TfIdfHelper();
		double tfidf = calculator.getTfIdf(doc1, documents, "computer");
		System.out.println("TF-IDF (computer) = " + tfidf);

	}

}
