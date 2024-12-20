SERVER_ALIAS=server
SERVER_DIR=../server-app/src/main/resources/config
SERVER_KEY_STORE=$SERVER_DIR/server-key-store.p12
SERVER_TRUST_STORE=$SERVER_DIR/server-trust-store.p12
SERVER_KEY_PASSWORD=server
SERVER_STORE_PASSWORD=server
SERVER_DN="CN=Server,OU=Server,O=InnovationForge,L=Arnhem,S=GLD,C=NL"
SERVER_CERT=server-public.cer
SERVER_CLIENT_CERT_ALIAS=client-public-cert

CLIENT_ALIAS=client
CLIENT_DIR=../client-app/src/main/resources/config
CLIENT_KEY_STORE=$CLIENT_DIR/client-key-store.p12
CLIENT_TRUST_STORE=$CLIENT_DIR/client-trust-store.p12
CLIENT_KEY_PASSWORD=client
CLIENT_STORE_PASSWORD=client
CLIENT_DN="CN=Client,OU=Client,O=Examples,L=CA,S=CA,C=US"
CLIENT_CERT=client-public.cer
CLIENT_SERVER_CERT_ALIAS=server-public-cert

# Delete existing keys
rm -r $SERVER_KEY_STORE
rm -r $SERVER_TRUS_TSTORE
rm -r $CLIENT_KEY_STORE
rm -r $SERVER_TRUST_STORE

# Generate server certificate and keystore
keytool -genkeypair -alias $SERVER_ALIAS -keyalg RSA -keysize 2048 -dname $SERVER_DN -storetype PKCS12 -keypass $SERVER_KEY_PASSWORD -keystore $SERVER_KEY_STORE -storepass $SERVER_STORE_PASSWORD -validity 3650

# Generate client certificate and keystore
keytool -genkeypair -alias $CLIENT_ALIAS -keyalg RSA -keysize 2048 -dname $CLIENT_DN -storetype PKCS12 -keypass $CLIENT_KEY_PASSWORD -keystore $CLIENT_KEY_STORE -storepass $CLIENT_STORE_PASSWORD -validity 3650

# Export the server public key
keytool -exportcert -alias $SERVER_ALIAS -file $SERVER_CERT -keystore $SERVER_KEY_STORE -storepass $SERVER_STORE_PASSWORD

# Export the client public key
keytool -exportcert -alias $CLIENT_ALIAS -file $CLIENT_CERT -keystore $CLIENT_KEY_STORE -storepass $CLIENT_STORE_PASSWORD

# Import server's public key into client's truststore
keytool -importcert -keystore $SERVER_TRUST_STORE -alias $SERVER_CLIENT_CERT_ALIAS -file $CLIENT_CERT -storepass $SERVER_STORE_PASSWORD -noprompt

# Import client's public key into server's truststore
keytool -importcert -keystore $CLIENT_TRUST_STORE -alias $CLIENT_SERVER_CERT_ALIAS -file $SERVER_CERT -storepass $CLIENT_STORE_PASSWORD -noprompt
