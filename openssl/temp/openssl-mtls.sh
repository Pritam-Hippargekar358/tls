#!/bin/bash
# Set variables
SERVER_DN="/C=US/ST=California/L=ayush/O=ravan/OU=IT/CN=localclient"
CLIENT_DN="/C=US/ST=California/L=ayush/O=ravan/OU=IT/CN=localclient"
# Delete existing files (if they exist)
# rm -f server-key-store.p12
# rm -f server-trust-store.p12
# rm -f client-key-store.p12
# rm -f client-trust-store.p12
# rm -f server-public.cer
# rm -f client-public.cer
# 1. Generate server private key and certificate signing request (CSR)
#openssl req -newkey rsa:2048 -nodes -keyout server-key.pem -out server.csr -subj "$SERVER_DN"
# 2. Generate self-signed server certificate
#openssl x509 -req -days 3650 -in server.csr -signkey server-key.pem -out server-public.cer
# 3. Create the server PKCS12 keystore containing the private key and server certificate
openssl pkcs12 -export -in server-public.cer -inkey server-key.pem -certfile server-public.cer -out server-key-store.p12 -passout pass:server
# 4. Generate client private key and certificate signing request (CSR)
#openssl req -newkey rsa:2048 -nodes -keyout client-key.pem -out client.csr -subj "$CLIENT_DN"
# 5. Generate self-signed client certificate
#openssl x509 -req -days 3650 -in client.csr -signkey client-key.pem -out client-public.cer
# 6. Create the client PKCS12 keystore containing the private key and client certificate
#openssl pkcs12 -export -in client-public.cer -inkey client-key.pem -out client-key-store.p12 -passout pass:client
# 7. Export the server's public certificate (the server's public key) in PEM format
#openssl x509 -in server-public.cer -out server-public.pem
# 8. Export the client's public certificate (the client's public key) in PEM format
#openssl x509 -in client-public.cer -out client-public.pem
# 9. Import the server's public certificate into the client's trust store (using the server's certificate)
#openssl pkcs12 -export -in server-public.pem -out client-trust-store.p12 -passout pass:client
# 10. Import the client's public certificate into the server's trust store (using the client's certificate)
#openssl pkcs12 -export -in client-public.pem -out server-trust-store.p12 -passout pass:server
# Clean up the intermediate files (CSR, private keys) as they are no longer needed
#rm -f server.csr
#rm -f client.csr
#rm -f server-key.pem
#rm -f client-key.pem
echo "Certificates and keystores created successfully."
