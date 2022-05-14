package udb.fp180271dsm.calculadorasalariossv;

import java.io.Serializable;
import java.util.Date;

public class HistoricoModel implements Serializable {

    private double salarioBruto;
    private String tipoContrato;
    private String porcentajeAFP;
    private String porcentajeISSS;
    private String porcentajeRenta;
    private Double AFP;
    private Double ISSS;
    private Double Renta;
    private Double salarioLiquidoMensual;
    private Double salarioLiquidoQuincenal;
    private Date fechaHistorico;
    private String idUsuario;

    public HistoricoModel(){ }

    public HistoricoModel(Double salarioBruto, String tipoContrato, String porcentajeAFP, String porcentajeISSS, String porcentajeRenta, Double AFP, Double ISSS, Double renta, Double salarioLiquidoMensual, Double salarioLiquidoQuincenal, Date fechaHistorico, String idUsuario) {
        this.salarioBruto = salarioBruto;
        this.tipoContrato = tipoContrato;
        this.porcentajeAFP = porcentajeAFP;
        this.porcentajeISSS = porcentajeISSS;
        this.porcentajeRenta = porcentajeRenta;
        this.AFP = AFP;
        this.ISSS = ISSS;
        Renta = renta;
        this.salarioLiquidoMensual = salarioLiquidoMensual;
        this.salarioLiquidoQuincenal = salarioLiquidoQuincenal;
        this.fechaHistorico = fechaHistorico;
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaHistorico() {
        return fechaHistorico;
    }

    public void setFechaHistorico(Date fechaHistorico) {
        this.fechaHistorico = fechaHistorico;
    }

    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getPorcentajeAFP() {
        return porcentajeAFP;
    }

    public void setPorcentajeAFP(String porcentajeAFP) {
        this.porcentajeAFP = porcentajeAFP;
    }

    public String getPorcentajeISSS() {
        return porcentajeISSS;
    }

    public void setPorcentajeISSS(String porcentajeISSS) {
        this.porcentajeISSS = porcentajeISSS;
    }

    public String getPorcentajeRenta() {
        return porcentajeRenta;
    }

    public void setPorcentajeRenta(String porcentajeRenta) {
        this.porcentajeRenta = porcentajeRenta;
    }

    public Double getAFP() {
        return AFP;
    }

    public void setAFP(Double AFP) {
        this.AFP = AFP;
    }

    public Double getISSS() {
        return ISSS;
    }

    public void setISSS(Double ISSS) {
        this.ISSS = ISSS;
    }

    public Double getRenta() {
        return Renta;
    }

    public void setRenta(Double renta) {
        Renta = renta;
    }

    public Double getSalarioLiquidoMensual() {
        return salarioLiquidoMensual;
    }

    public void setSalarioLiquidoMensual(Double salarioLiquidoMensual) {
        this.salarioLiquidoMensual = salarioLiquidoMensual;
    }

    public Double getSalarioLiquidoQuincenal() {
        return salarioLiquidoQuincenal;
    }

    public void setSalarioLiquidoQuincenal(Double salarioLiquidoQuincenal) {
        this.salarioLiquidoQuincenal = salarioLiquidoQuincenal;
    }
}
