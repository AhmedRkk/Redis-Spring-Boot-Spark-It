FROM redis:latest

# Install build dependencies
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        build-essential \
        git

# Clone and build RedisJSON
RUN git clone https://github.com/RedisJSON/RedisJSON.git && \
    cd RedisJSON && \
    make && \
    make install PREFIX=/usr/local/

# Expose Redis port
EXPOSE 11000

# Start Redis server with RedisJSON module loaded
CMD ["redis-server", "--loadmodule", "/usr/local/lib/redis/modules/redisjson.so"]
