package GoBi1;

public class Attributes{

	String gene_id;
	String transcript_id;
	String exon_number;
	String gene_name;
	String gene_source;
	String gene_biotype;
	String transcript_name;
	String transcript_source;
	String exon_id;
	String[] tags;

	// Constructor for Exon
	public Attributes(String gene_id, String transcript_id, String exon_number, String gene_name, String gene_source,
			String gene_biotype, String transcript_name, String transcript_source, String exon_id, String[] tags) {
		this.gene_id = gene_id;
		this.transcript_id = transcript_id;
		this.exon_number = exon_number;
		this.gene_name = gene_name;
		this.gene_source = gene_source;
		this.gene_biotype = gene_biotype;
		this.transcript_name = transcript_name;
		this.transcript_source = transcript_source;
		this.exon_id = exon_id;
		this.tags = tags;
	}

	// Constructor for Transcript
	public Attributes(String gene_id, String transcript_id, String gene_name, String gene_source, String gene_biotype,
			String transcript_name, String transcript_source, String[] tags) {
		this.gene_id = gene_id;
		this.transcript_id = transcript_id;
		this.gene_name = gene_name;
		this.gene_source = gene_source;
		this.gene_biotype = gene_biotype;
		this.transcript_name = transcript_name;
		this.transcript_source = transcript_source;
		this.tags = tags;
	}

	// Constructor for Gene
	public Attributes(String gene_id, String gene_name, String gene_source, String gene_biotype, String[] tags) {
		this.gene_id = gene_id;
		this.gene_name = gene_name;
		this.gene_source = gene_source;
		this.gene_biotype = gene_biotype;
		this.tags = tags;
	}

}
