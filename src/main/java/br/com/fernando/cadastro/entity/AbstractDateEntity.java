package br.com.fernando.cadastro.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.hibernate.envers.Audited;

/**
 *
 * @author fernando
 */
@MappedSuperclass
public class AbstractDateEntity {

    @Audited
    @Column(name = "DATINS", updatable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime datins;

    @Audited
    @Column(name = "DATALT", columnDefinition = "TIMESTAMP")
    private LocalDateTime datalt;

    @PrePersist
    protected void onCreate() {
        if (datins == null) {
            datins = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        datalt = LocalDateTime.now();
    }

    public LocalDateTime getDatins() {
        return datins;
    }

    public void setDatins(LocalDateTime datins) {
        this.datins = datins;
    }

    public LocalDateTime getDatalt() {
        return datalt;
    }

    public void setDatalt(LocalDateTime datalt) {
        this.datalt = datalt;
    }
}