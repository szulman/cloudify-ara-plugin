import setuptools


setuptools.setup(
    zip_safe=False,
    name='cloudify-ara-plugin',
    version='1.0.0',
    author='szp',
    author_email='peter.szulman@automic.com',
    packages=['ara_plugin'],
    license='LICENSE',
    description='Cloudify ARA plugin',
    install_requires=[
        'cloudify-plugins-common>=3.3.1',
        'requests',
    ],
)
