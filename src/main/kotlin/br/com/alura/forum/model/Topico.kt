package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    // notação data não é obrigatória
    //se iniciarmos as variáveis dentro da entidade conseguimos instaciar um objeto sem precisa passar os dados no construtor. Ex.: var titulo: String = ''

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null, //id é opcional, usamos "?" para dizer isso
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val curso: Curso,

    @ManyToOne
    val autor: Usuario,

    @Enumerated(value = EnumType.STRING)
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList()
)

