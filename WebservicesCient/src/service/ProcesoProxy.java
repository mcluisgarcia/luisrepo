package service;

public class ProcesoProxy implements service.Proceso {
  private String _endpoint = null;
  private service.Proceso proceso = null;
  
  public ProcesoProxy() {
    _initProcesoProxy();
  }
  
  public ProcesoProxy(String endpoint) {
    _endpoint = endpoint;
    _initProcesoProxy();
  }
  
  private void _initProcesoProxy() {
    try {
      proceso = (new service.ProcesoServiceLocator()).getProcesoPort();
      if (proceso != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)proceso)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)proceso)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (proceso != null)
      ((javax.xml.rpc.Stub)proceso)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public service.Proceso getProceso() {
    if (proceso == null)
      _initProcesoProxy();
    return proceso;
  }
  
  public java.lang.String sayHello() throws java.rmi.RemoteException{
    if (proceso == null)
      _initProcesoProxy();
    return proceso.sayHello();
  }
  
  
}