package service;

public class NoticiasProxy implements service.Noticias {
  private String _endpoint = null;
  private service.Noticias noticias = null;
  
  public NoticiasProxy() {
    _initNoticiasProxy();
  }
  
  public NoticiasProxy(String endpoint) {
    _endpoint = endpoint;
    _initNoticiasProxy();
  }
  
  private void _initNoticiasProxy() {
    try {
      noticias = (new service.NoticiasServiceLocator()).getNoticiasPort();
      if (noticias != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)noticias)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)noticias)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (noticias != null)
      ((javax.xml.rpc.Stub)noticias)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public service.Noticias getNoticias() {
    if (noticias == null)
      _initNoticiasProxy();
    return noticias;
  }
  
  public service.NewsDTO generarNoticias() throws java.rmi.RemoteException{
    if (noticias == null)
      _initNoticiasProxy();
    return noticias.generarNoticias();
  }
  
  
}