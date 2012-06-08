/**
 * 
 */
package crypto;

/**
 * @author tylergarlick
 *
 */
public enum EncryptionType {
	MD2("MD2"),
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");
	
	public final String algorithm;

	private EncryptionType(String algorithm){
		this.algorithm = algorithm;
	}
	
}
