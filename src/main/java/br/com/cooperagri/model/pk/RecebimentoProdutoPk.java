package br.com.cooperagri.model.pk;

import java.io.Serializable;

import br.com.cooperagri.model.Produto;
import br.com.cooperagri.model.Recebimento;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class RecebimentoProdutoPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "recebimento_id")
    private Recebimento recebimento;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}